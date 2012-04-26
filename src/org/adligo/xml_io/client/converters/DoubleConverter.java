package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class DoubleConverter implements I_Converter<Double>, I_AttributeConverter<Double> {

	@Override
	public ObjectFromXml<Double> fromXml(String xml, TagInfo info,Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Double>(Double.parseDouble(text));
	}

	@Override
	public void toXml(Double p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagWithTextContent(Tags.DOUBLE, "" + p);
	}

	@Override
	public Double fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Double.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Double p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
