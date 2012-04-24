package org.adligo.xml_io.client.converters;

import java.math.BigInteger;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BigIntegerConverter implements I_Converter<BigInteger> {

	@Override
	public BigInteger fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new BigInteger(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, BigInteger p, Xml_IOWriterContext context) {
		builder.appendTagWithTextContent(ClassMappings.BIG_INTEGER_TAG, 
					p.toString());
	}

}
