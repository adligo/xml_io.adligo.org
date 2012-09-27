package org.adligo.xml_io.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.adligo.i.util.client.StringUtils;

public class NamespaceConvertersMutant implements I_NamespaceConverters {
	public static final String NAMESPACE_CONVERTERS_MUTANT_REQUIRES_A_PACKAGE_NAME = "NamespaceConvertersMutant requires a packageName!";
	public static final String NAMESPACE_CONVERTERS_MUTANT_REQUIRES_A_NAMESPACE = "NamespaceConvertersMutant requires a namespace!";
	private String namespace;
	private String packageName;
	
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

	public NamespaceConvertersMutant() {}
	
	public NamespaceConvertersMutant(I_NamespaceConverters p) {
		namespace = p.getNamespace();
		if (StringUtils.isEmpty(namespace)) {
			throw new IllegalStateException(NAMESPACE_CONVERTERS_MUTANT_REQUIRES_A_NAMESPACE);
		}
		packageName = p.getPackageName();
		if (StringUtils.isEmpty(packageName)) {
			throw new IllegalStateException(NAMESPACE_CONVERTERS_MUTANT_REQUIRES_A_PACKAGE_NAME);
		}
		xmlToObjectConverters.putAll(p.getXmlToObjectConverters());
		attributeConverters.putAll(p.getAttributeConverters());
		objectToXmlConverters.putAll(p.getObjectToXmlConverters());
	}
	
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public Map<String, I_Converter<?>> getXmlToObjectConverters() {
		return Collections.unmodifiableMap(xmlToObjectConverters);
	}
	
	public I_Converter<?> getXmlToObjectConverter(String tagName) {
		return xmlToObjectConverters.get(tagName);
	}
	
	public Map<Class<?>, I_AttributeConverter<?>> getAttributeConverters() {
		return Collections.unmodifiableMap(attributeConverters);
	}
	
	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return attributeConverters.get(clazz);
	}
	
	
	public Map<Class<?>, I_Converter<?>> getObjectToXmlConverters() {
		return Collections.unmodifiableMap(objectToXmlConverters);
	}
	
	public I_Converter<?> getObjectToXmlConverter(Class<?> c) {
		return objectToXmlConverters.get(c);
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
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
