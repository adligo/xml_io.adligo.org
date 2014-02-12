package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BigDecimalConverter implements I_Converter<BigDecimal>, I_AttributeConverter<BigDecimal> {

	@Override
	public ObjectFromXml<BigDecimal> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<BigDecimal>(new BigDecimal(text));
	}

	@Override
	public void toXml(BigDecimal p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.BIG_DECIMAL_TAG_SUFFIX, p.toString());
	}

	@Override
	public BigDecimal fromXmlAttribute(String attributeValue, Xml_IOReaderContext context) {
		return new BigDecimal(attributeValue);
	}

	@Override
	public void toXmlAttribute(BigDecimal p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String name = context.getNextTagNameAttribute();
		builder.appendAttribute(name, p.toString());
	}

}
