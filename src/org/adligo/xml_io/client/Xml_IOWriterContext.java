package org.adligo.xml_io.client;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;

public class Xml_IOWriterContext {
	public static final String COULD_NOT_FIND_A_CONVERTER_FOR_CLASS = 
		"Could not find a converter for class ";
	
	private Xml_IOWriter writer;
	private I_XMLBuilder builder = new XMLBuilder();
	private Xml_IOSettings settings;
	
	Xml_IOWriterContext() {}
	
	public Xml_IOWriter getWriter() {
		return writer;
	}
	void setWriter(Xml_IOWriter writer) {
		this.writer = writer;
	}
	public Xml_IOSettings getSettings() {
		return settings;
	}
	void setSettings(Xml_IOSettings settings) {
		this.settings = settings;
	}
	
	@SuppressWarnings("unchecked")
	public void writeXml(Object p) {
		if (p == null) {
			return;
		}
		Class<?> clazz = p.getClass();
		I_Converter<Object> converter = (I_Converter<Object>) 
					settings.getToXmlConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_CONVERTER_FOR_CLASS 
					+ clazz);
		}
		converter.toXml(builder, p, this);
	}

	public I_XMLBuilder getBuilder() {
		return builder;
	}
}
