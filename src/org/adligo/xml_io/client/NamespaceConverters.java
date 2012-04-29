package org.adligo.xml_io.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NamespaceConverters {
	private String namespace;
	/**
	 * the tag with out a namespace prefix ie i in <i> to the converter
	 */
	private Map<String, I_Converter<?>> xmlToObjectConverters = 
		new HashMap<String, I_Converter<?>>();
	/**
	 * the attribute class to its converter for reading or writing 
	 * xml attributes.
	 */
	private Map<Class<?>, I_AttributeConverter<?>> attributeConverters = 
		new HashMap<Class<?>, I_AttributeConverter<?>>();
	/**
	 * the class to the converter for writing xml.
	 */
	private Map<Class<?>, I_Converter<?>> objectToXmlConverters = 
		new HashMap<Class<?>, I_Converter<?>>();

	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public Set<Entry<String, I_Converter<?>>> getXmlToObjectConverters() {
		return xmlToObjectConverters.entrySet();
	}
	
	public Set<Entry<Class<?>, I_AttributeConverter<?>>> getAttributeConverters() {
		return attributeConverters.entrySet();
	}
	
	public Set<Entry<Class<?>, I_Converter<?>>> getObjectToXmlConverters() {
		return objectToXmlConverters.entrySet();
	}
	
	public void addXmlToObjectConverter(String p, I_Converter<?> c) {
		xmlToObjectConverters.put(p, c);
	}
	
	public void addXmlAttributeToObjectConverter(Class<?> c, I_AttributeConverter<?> ac) {
		attributeConverters.put(c, ac);
	}
	
	public void addObjectToXmlConverter(Class<?> c, I_Converter<?> v) {
		objectToXmlConverters.put(c, v);
	}
}
