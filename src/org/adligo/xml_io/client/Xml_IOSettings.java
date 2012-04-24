package org.adligo.xml_io.client;

import java.util.HashMap;
import java.util.Map;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.converters.ClassMappings;

public class Xml_IOSettings {
	private I_XMLBuilder builder = new XMLBuilder();
	private Map<String, I_Converter<?>> xmlToObjectConverters = 
		new HashMap<String, I_Converter<?>>();
	private Map<Class<?>, I_Converter<?>> objectToXmlConverters = 
		new HashMap<Class<?>, I_Converter<?>>();
	
	public Xml_IOSettings() {
		xmlToObjectConverters.putAll(ClassMappings.DEFAULT_XML_TO_OBJECT_CONVERTERS);
		objectToXmlConverters.putAll(ClassMappings.DEFAULT_OBJECT_TO_XML_CONVERTERS);
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
}
