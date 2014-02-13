package org.adligo.xml_io.shared;

import java.util.Map;

/**
 * A instance of I_NamespaceConverters contains a bunch of converters one per class
 * in the namespace, and is not contextual to the xml.
 * 
 * NOTE none of the methods in this class should allow mutation of the instance.
 * 
 * @author scott
 *
 */
public interface I_NamespaceConverters {
	/**
	 * the xml namespace ie "http://www.adligo.org/xml_io"
	 * @return
	 */
	public String getNamespace();
	
	public Map<String, I_Converter<?>> getXmlToObjectConverters() ;
	
	public I_Converter<?> getXmlToObjectConverter(String tagName);
	
	public Map<Class<?>, I_AttributeConverter<?>> getAttributeConverters();
	
	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz);
	
	public Map<Class<?>, I_Converter<?>> getObjectToXmlConverters();
	
	public I_Converter<?> getObjectToXmlConverter(Class<?> c);
	
	/**
	 * the java package name represented as a string ie "java.lang"
	 * @return
	 */
	public String getPackageName();
}
