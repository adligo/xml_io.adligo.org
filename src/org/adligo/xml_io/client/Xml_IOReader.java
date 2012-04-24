package org.adligo.xml_io.client;

import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;


public class Xml_IOReader {


	public Object readXml(String xml) {
		return readXml(xml, new Xml_IOSettings());
	}
	
	public Object readXml(String xml, Xml_IOSettings settings) {
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		I_Converter<?> converter = settings.getFromXmlConverter(tagName);
		if (converter == null) {
			throw new IllegalArgumentException("Could not find a converter for tag " 
					+ tagName);
		}
		Xml_IOReaderContext context = new Xml_IOReaderContext();
		context.setReader(this);
		context.setSettings(settings);
		return converter.fromXml(xml, info, context);
	}

	
}
