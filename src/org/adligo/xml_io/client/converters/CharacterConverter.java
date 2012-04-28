package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class CharacterConverter implements I_Converter<Character>, I_AttributeConverter<Character> {

	@Override
	public ObjectFromXml<Character> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		String result = Parser.unescapeFromXml(text);
		return new ObjectFromXml<Character>(result.charAt(0));
	}

	@Override
	public void toXml(Character text, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String escapedText = Parser.escapeForXml("" + text);
		builder.appendTagWithTextContent(Xml_IOConstants.CHARACTER_TAG_SUFFIX, escapedText);
	}
	
	@Override
	public Character fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		String toRet = Parser.unescapeFromXml(attributeValue);
		return toRet.toCharArray()[0];
	}

	@Override
	public void toXmlAttribute(Character p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		String val = Parser.escapeForXml("" + p);
		builder.appendAttribute(attributeName, val);
	}


}
