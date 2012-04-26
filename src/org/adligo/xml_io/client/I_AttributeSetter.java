package org.adligo.xml_io.client;


/**
 * this interface is used to map attributes back to fields
 * in CustomConverters (see CustomModelConverter in the xml_io_tests project).
 * 
 * @author scott
 *
 */
public interface I_AttributeSetter<T> {

	public void set(T obj, String unescapedAttributeValue,Xml_IOReaderContext context);
}
