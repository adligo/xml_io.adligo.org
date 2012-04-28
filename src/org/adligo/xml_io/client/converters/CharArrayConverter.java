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

public class CharArrayConverter implements I_Converter<char []>, I_AttributeConverter<char[]> {

	@Override
	public ObjectFromXml<char[]> fromXml(String xml, TagInfo info,
			Xml_IOReaderContext context) {
		
		String text = Parser.getTextContent(xml, info);
		text = Parser.unescapeFromXml(text);
		return new ObjectFromXml<char [] > (text.toCharArray());
	}
	@Override
	public void toXml(char[] p, Xml_IOWriterContext context) {
		String chars = new String(p);
		chars = Parser.escapeForXml(chars);
		context.appendDefaultTag(Xml_IOConstants.CHAR_ARRAY_TAG_SUFFIX, chars);
	}

	@Override
	public char [] fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		String toRet = Parser.unescapeFromXml(attributeValue);
		return toRet.toCharArray();
	}

	@Override
	public void toXmlAttribute(char [] p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		String val = Parser.escapeForXml(new String(p));
		builder.appendAttribute(attributeName, val);
	}


}
