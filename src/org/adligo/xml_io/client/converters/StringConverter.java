package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class StringConverter implements I_Converter<String>{

	@Override
	public String fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return Parser.unescapeFromXml(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, String text, Xml_IOWriterContext context) {
		String escapedText = Parser.escapeForXml(text);
		builder.appendTagWithTextContent(ClassMappings.STRING_TAG, escapedText);
	}

}
