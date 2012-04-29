package org.adligo.xml_io.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ConverterConfigurationMutant implements I_ConverterConfiguration {
	public static final String NO_PREFIX_FOUND_FOR_NAMESPACE = "No prefix found for namespace ";

	/**
	 * the map of namespaces to prefixes
	 */
	private Map<String, String> namespaceToPrefix = new HashMap<String, String>();
	
	/**
	 * the xml tag with the namespace prefix ie a:i
	 * to the converter for reading in xml.
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
	
	public ConverterConfigurationMutant() {}
	
	public ConverterConfigurationMutant(ConverterConfigurationMutant other) {
		namespaceToPrefix.putAll(other.namespaceToPrefix);
		xmlToObjectConverters.putAll(other.xmlToObjectConverters);
		attributeConverters.putAll(other.attributeConverters);
		objectToXmlConverters.putAll(other.objectToXmlConverters);
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_ConverterConfiguration#getPrefix(java.lang.String)
	 */
	@Override
	public String getPrefix(String namespace) {
		String toRet = namespaceToPrefix.get(namespace);
		if (toRet == null) {
			throw new NullPointerException(NO_PREFIX_FOUND_FOR_NAMESPACE + namespace);
		}
		return toRet;
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_ConverterConfiguration#getFromXmlConverter(java.lang.String)
	 */
	@Override
	public I_Converter<?> getFromXmlConverter(String tag) {
		return xmlToObjectConverters.get(tag);
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_ConverterConfiguration#getToXmlConverter(java.lang.Class)
	 */
	@Override
	public I_Converter<?> getToXmlConverter(Class<?> clazz) {
		return objectToXmlConverters.get(clazz);
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_ConverterConfiguration#getAttributeConverter(java.lang.Class)
	 */
	@Override
	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return attributeConverters.get(clazz);
	}
	
	public void addNamespacePrefix(String prefix, String namespace) {
		namespaceToPrefix.put(namespace, prefix);
	}
	
	public void addNamespaceConverters(NamespaceConverters nc) {
		String namespace = nc.getNamespace();
		String prefix = getPrefix(namespace);
		
		Set<Entry<String, I_Converter<?>>> converters =  nc.getXmlToObjectConverters();
		for (Entry<String, I_Converter<?>> e: converters) {
			String tag = e.getKey();
			I_Converter<?> con = e.getValue();
			xmlToObjectConverters.put(prefix + ":" + tag, con);
		}
		
		Set<Entry<Class<?>, I_AttributeConverter<?>>> attribConvertes =  nc.getAttributeConverters();
		for (Entry<Class<?>, I_AttributeConverter<?>> e: attribConvertes) {
			Class<?> clazz = e.getKey();
			I_AttributeConverter<?> con = e.getValue();
			attributeConverters.put(clazz, con);
		}
		
		Set<Entry<Class<?>, I_Converter<?>>> toXmlConverters =  nc.getObjectToXmlConverters();
		for (Entry<Class<?>, I_Converter<?>> e: toXmlConverters) {
			Class<?> clazz = e.getKey();
			I_Converter<?> con = e.getValue();
			objectToXmlConverters.put(clazz, con);
		}
	}
}
