package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class CharacterConverter implements I_Converter<Character>{

	@Override
	public Character fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		String result = Parser.unescapeFromXml(text);
		return result.charAt(0);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Character text, Xml_IOWriterContext context) {
		String escapedText = Parser.escapeForXml("" + text);
		builder.appendTagWithTextContent(ClassMappings.CHARACTER_TAG, escapedText);
	}

}
