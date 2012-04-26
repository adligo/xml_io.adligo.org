package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class CharArrayConverter implements I_Converter<char []>{

	@Override
	public char[] fromXml(String xml, TagInfo info,
			Xml_IOReaderContext context) {
		
		String text = Parser.getTextContent(xml, info);
		text = Parser.unescapeFromXml(text);
		return text.toCharArray();
	}
	@Override
	public void toXml(I_XMLBuilder builder, char[] p,
			Xml_IOWriterContext context) {
		
		builder.appendStartTag(ClassMappings.CHAR_ARRAY_TAG);
		builder.appendTagHeaderEnd(false);
		String chars = new String(p);
		chars = Parser.escapeForXml(chars);
		builder.append(chars);
		builder.appendEndTag(ClassMappings.CHAR_ARRAY_TAG);
	}

}
