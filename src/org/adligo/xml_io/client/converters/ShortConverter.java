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

public class ShortConverter implements I_Converter<Short>, I_AttributeConverter<Short> {

	@Override
	public ObjectFromXml<Short> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Short>(Short.parseShort(text));
	}

	@Override
	public void toXml(Short p,Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagWithTextContent(Xml_IOConstants.SHORT_TAG_SUFFIX, "" + p);
	}

	@Override
	public Short fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Short.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Short p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
