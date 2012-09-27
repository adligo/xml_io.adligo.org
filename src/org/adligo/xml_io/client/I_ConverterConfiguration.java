package org.adligo.xml_io.client;

public interface I_ConverterConfiguration {

	public abstract String getPrefix(String namespace);

	public abstract I_Converter<?> getFromXmlConverter(Xml_IOTagContext tag);

	public abstract I_Converter<?> getToXmlConverter(Class<?> clazz);

	public abstract I_AttributeConverter<?> getAttributeConverter(Class<?> clazz);

}