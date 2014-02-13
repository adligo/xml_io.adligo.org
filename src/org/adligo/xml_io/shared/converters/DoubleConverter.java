package org.adligo.xml_io.shared.converters;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.shared.I_AttributeConverter;
import org.adligo.xml_io.shared.I_Converter;
import org.adligo.xml_io.shared.ObjectFromXml;
import org.adligo.xml_io.shared.Xml_IOConstants;
import org.adligo.xml_io.shared.Xml_IOReaderContext;
import org.adligo.xml_io.shared.Xml_IOWriterContext;

public class DoubleConverter implements I_Converter<Double>, I_AttributeConverter<Double> {

	@Override
	public ObjectFromXml<Double> fromXml(String xml, TagInfo info,Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Double>(Double.parseDouble(text));
	}

	@Override
	public void toXml(Double p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.DOUBLE_TAG_SUFFIX, "" + p);
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
