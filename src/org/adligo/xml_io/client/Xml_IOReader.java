package org.adligo.xml_io.client;

import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.converters.I_Converter;


public class Xml_IOReader {


	public Object readXml(String xml) {
		return readXml(xml, new XmlIOSettings());
	}
	
	public Object readXml(String xml, XmlIOSettings settings) {
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		I_Converter<?> converter = settings.getFromXmlConverter(tagName);
		if (converter == null) {
			throw new IllegalArgumentException("Could not find a converter for tag " 
					+ tagName);
		}
		return converter.fromXml(xml, info, settings);
	}

	
}
