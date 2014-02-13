package org.adligo.xml_io.shared;

import java.util.HashMap;
import java.util.Map;

import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;


public class Xml_IOReaderContext {
	public static final String COULD_NOT_FIND_A_CONVERTER_FOR_TAG = 
		"Could not find a converter for tag ";
	public static final String COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS = 
		"Could not find a attribute converter for class ";
	
	private Xml_IOReader reader;
	private I_Xml_IOSettings settings;
	private Map<String,String> nsPrefixToNamespace = new HashMap<String, String>();
	
	/**
	 * if we are in the namespace tag
	 */
	private boolean namespaceTag = true;
	
	public Xml_IOReader getReader() {
		return reader;
	}

	void setReader(Xml_IOReader reader) {
		this.reader = reader;
	}

	public I_Xml_IOSettings getSettings() {
		return settings;
	}

	void setSettings(I_Xml_IOSettings settings) {
		this.settings = settings;
	}
	
	public ObjectFromXml<?> readXml(String xml) {
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		
		Xml_IOTagContext tagCtx = getTagContext(tagName);
		I_Converter<?> converter = settings.getFromXmlConverter(tagCtx);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_CONVERTER_FOR_TAG 
					+ tagName);
		}
		return converter.fromXml(xml, info, this);
	}
	
	public Xml_IOTagContext getTagContext(String tagWithColon) {
		String ns = Xml_IOTagContext.DEFAULT_NAMESPACE;
		String tagName = tagWithColon;
		
		int colon = tagWithColon.indexOf(":");
		if (colon != -1) {
			if (colon + 1 <= tagWithColon.length()) {
				tagName = tagWithColon.substring(colon + 1, tagWithColon.length());
				String nsPre = tagWithColon.substring(0, colon);
				if (settings.isIgnoreFileNamespace()) {
					ns = settings.getNamespaceForDefaultPrefix(nsPre);
				} else {
					ns = nsPrefixToNamespace.get(nsPre);
				}
				if (ns == null) {
					throw new IllegalArgumentException("Was unable to find a namespace for prefix " + nsPre);
				}
			}
		}
		return new Xml_IOTagContext(tagName, ns);
	}

	public Object readAttribute(Class<?> clazz, String unescapedAttributeValue) {
	
		I_AttributeConverter<?> converter = settings.getAttributeConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS
					+ clazz);
		}
		return converter.fromXmlAttribute(unescapedAttributeValue, this);
	}
	
	public void noLongerInNamespaceTag() {
		namespaceTag = false;
	}
	
	public boolean inNamespaceTag() {
		return namespaceTag;
	}
	
	void addNamespace(String prefix, String namespace) {
		nsPrefixToNamespace.put(prefix, namespace);
	}
	
	public String getNamespace(String nsPrefix) {
		return nsPrefixToNamespace.get(nsPrefix);
	}
}
