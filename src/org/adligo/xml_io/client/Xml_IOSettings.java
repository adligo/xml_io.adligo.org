package org.adligo.xml_io.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.converters.ClassMappings;

public class Xml_IOSettings {
	private I_XMLBuilder builder = new XMLBuilder();
	private Map<String, I_Converter<?>> xmlToObjectConverters = 
		new HashMap<String, I_Converter<?>>();
	private Map<Class<?>, I_Converter<?>> objectToXmlConverters = 
		new HashMap<Class<?>, I_Converter<?>>();
	private Map<Class<?>, I_AttributeConverter<?>> xmlAttributeToObjectConverters = 
		new HashMap<Class<?>, I_AttributeConverter<?>>();
	private Map<Class<?>, I_AttributeConverter<?>> objectToXmlAttributeConverters = 
		new HashMap<Class<?>, I_AttributeConverter<?>>();
	/**
	 * map of namespaces to prefixes for writing of xml 
	 */
	private Map<String, String> namespaceMap = new HashMap<String, String>();
	private LetterCounter prefixCounter = new LetterCounter();
	
	private boolean includeXmlHeader = true;
	private boolean includeXmlSchemaInfoInFirstTag = true;
	
	public Xml_IOSettings() {
		xmlToObjectConverters.putAll(ClassMappings.DEFAULT_XML_TO_OBJECT_CONVERTERS);
		objectToXmlConverters.putAll(ClassMappings.DEFAULT_OBJECT_TO_XML_CONVERTERS);
	
		xmlAttributeToObjectConverters.putAll(ClassMappings.DEFAULT_XML_ATTRIBUTE_TO_OBJECT_CONVERTERS);
		objectToXmlAttributeConverters.putAll(ClassMappings.DEFAULT_OBJECT_TO_XML_ATTRIBUTE_CONVERTERS);
		String prefix = prefixCounter.getNextId();
		namespaceMap.put(Xml_IOConstants.DEFAULT_NAMESPACE, prefix);
	}
	
	public I_XMLBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(I_XMLBuilder builder) {
		this.builder = builder;
	}
	
	public I_Converter<?> getToXmlConverter(Class<?> c) {
		return objectToXmlConverters.get(c);
	}
	
	public I_Converter<?> getFromXmlConverter(String tag) {
		return xmlToObjectConverters.get(tag);
	}
	
	public void addToXmlConverters( Map<Class<?>, I_Converter<?>> converters) {
		objectToXmlConverters.putAll(converters);
	}
	
	public void addFromXmlConverters( Map<String, I_Converter<?>> converters) {
		xmlToObjectConverters.putAll(converters);
	}
	
	public I_AttributeConverter<?> getToXmlAttributeConverter(Class<?> c) {
		return xmlAttributeToObjectConverters.get(c);
	}
	
	public I_AttributeConverter<?> getFromXmlAttributeConverter(Class<?> c) {
		return objectToXmlAttributeConverters.get(c);
	}
	
	public void addToXmlAttributeConverters( Map<Class<?>, I_AttributeConverter<?>> converters) {
		objectToXmlAttributeConverters.putAll(converters);
	}
	
	public void addFromXmlAttributeConverters( Map<Class<?>, I_AttributeConverter<?>> converters) {
		xmlAttributeToObjectConverters.putAll(converters);
	}

	public boolean isIncludeXmlHeader() {
		return includeXmlHeader;
	}

	public void setIncludeXmlHeader(boolean includeXmlHeader) {
		this.includeXmlHeader = includeXmlHeader;
	}

	public boolean isIncludeXmlSchemaInfoInFirstTag() {
		return includeXmlSchemaInfoInFirstTag;
	}

	public void setIncludeXmlSchemaInfoInFirstTag(
			boolean includeXmlSchemaInfoInFirstTag) {
		this.includeXmlSchemaInfoInFirstTag = includeXmlSchemaInfoInFirstTag;
	}
	
	public Set<Entry<String, String>> getNamespaceEntries() {
		return namespaceMap.entrySet();
	}
	
	public String getNamespacePrefix(String namespace) {
		String toRet = namespaceMap.get(namespace);
		if (toRet == null) {
			throw new NullPointerException("No prefix found for namespace '" + namespace + "'");
		}
		return toRet;
	}
	
	public void addNamespace(String namespace) {
		String prefix = prefixCounter.getNextId();
		namespaceMap.put(namespace, prefix);
	}
}
