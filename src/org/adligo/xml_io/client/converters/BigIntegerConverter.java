package org.adligo.xml_io.client.converters;

import java.math.BigInteger;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class BigIntegerConverter implements I_Converter<BigInteger> {

	@Override
	public BigInteger fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return new BigInteger(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, BigInteger p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.BIG_INTEGER_TAG, 
					p.toString());
	}

}
