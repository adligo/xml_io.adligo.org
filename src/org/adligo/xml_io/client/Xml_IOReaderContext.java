package org.adligo.xml_io.client;

import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;


public class Xml_IOReaderContext {
	public static final String COULD_NOT_FIND_A_CONVERTER_FOR_TAG = 
		"Could not find a converter for tag ";
	public static final String COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS = 
		"Could not find a attribute converter for class ";
	
	private Xml_IOReader reader;
	private Xml_IOSettings settings;
	
	public Xml_IOReader getReader() {
		return reader;
	}

	void setReader(Xml_IOReader reader) {
		this.reader = reader;
	}

	public Xml_IOSettings getSettings() {
		return settings;
	}

	void setSettings(Xml_IOSettings settings) {
		this.settings = settings;
	}
	
	public ObjectFromXml<?> readXml(String xml) {
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		I_Converter<?> converter = settings.getFromXmlConverter(tagName);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_CONVERTER_FOR_TAG 
					+ tagName);
		}
		return converter.fromXml(xml, info, this);
	}

	public Object readAttribute(Class<?> clazz, String unescapedAttributeValue) {
	
		I_AttributeConverter<?> converter = settings.getAttributeConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS
					+ clazz);
		}
		return converter.fromXmlAttribute(unescapedAttributeValue, this);
	}
	
}
