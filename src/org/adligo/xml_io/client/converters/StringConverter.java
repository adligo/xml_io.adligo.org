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

public class StringConverter implements I_Converter<String>, I_AttributeConverter<String> {

	@Override
	public ObjectFromXml<String> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<String>(Parser.unescapeFromXml(text));
	}

	@Override
	public void toXml(String text, Xml_IOWriterContext context) {
		String escapedText = Parser.escapeForXml(text);
		context.appendDefaultTag(Xml_IOConstants.STRING_TAG_SUFFIX, escapedText);
	}

	@Override
	public String fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		String toRet = Parser.unescapeFromXml(attributeValue);
		return toRet;
	}

	@Override
	public void toXmlAttribute(String p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		String val = Parser.escapeForXml(p);
		builder.appendAttribute(attributeName, val);
	}
}
