package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class LongConverter implements I_Converter<Long>, I_AttributeConverter<Long> {

	@Override
	public ObjectFromXml<Long> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Long>(Long.parseLong(text));
	}

	@Override
	public void toXml(Long p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagWithTextContent(Tags.LONG, "" + p);
	}

	@Override
	public Long fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Long.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Long p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
