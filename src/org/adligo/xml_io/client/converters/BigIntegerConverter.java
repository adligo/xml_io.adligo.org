package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BigIntegerConverter implements I_Converter<BigInteger>, I_AttributeConverter<BigInteger> {

	@Override
	public ObjectFromXml<BigInteger> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<BigInteger>(new BigInteger(text));
	}

	@Override
	public void toXml(BigInteger p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.BIG_INTEGER_TAG_SUFFIX, p.toString());
	}

	@Override
	public BigInteger fromXmlAttribute(String attributeValue, Xml_IOReaderContext context) {
		return new BigInteger(attributeValue);
	}

	@Override
	public void toXmlAttribute(BigInteger p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String name = context.getNextTagNameAttribute();
		builder.appendAttribute(name, p.toString());
	}
}
