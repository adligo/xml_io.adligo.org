package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class StringConverter implements I_Converter<String>{

	@Override
	public String fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Parser.unescapeFromXml(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, String text, XmlIOSettings settings) {
		String escapedText = Parser.escapeForXml(text);
		builder.appendTagWithTextContent(ClassMappings.STRING_TAG, escapedText);
	}

}
