package org.adligo.xml_io.shared.converters;

import java.math.BigInteger;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.shared.I_AttributeConverter;
import org.adligo.xml_io.shared.I_Converter;
import org.adligo.xml_io.shared.ObjectFromXml;
import org.adligo.xml_io.shared.Xml_IOConstants;
import org.adligo.xml_io.shared.Xml_IOReaderContext;
import org.adligo.xml_io.shared.Xml_IOWriterContext;

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
