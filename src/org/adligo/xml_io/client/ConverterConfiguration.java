package org.adligo.xml_io.client;

public class ConverterConfiguration implements I_ConverterConfiguration {
	private ConverterConfigurationMutant mutant;
	
	public ConverterConfiguration() {}
	
	public ConverterConfiguration(ConverterConfigurationMutant data) {
		mutant = new ConverterConfigurationMutant(data);
	}

	public String getPrefix(String namespace) {
		return mutant.getPrefix(namespace);
	}

	public I_Converter<?> getFromXmlConverter(String tag) {
		return mutant.getFromXmlConverter(tag);
	}

	public I_Converter<?> getToXmlConverter(Class<?> clazz) {
		return mutant.getToXmlConverter(clazz);
	}

	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return mutant.getAttributeConverter(clazz);
	}
	
}
