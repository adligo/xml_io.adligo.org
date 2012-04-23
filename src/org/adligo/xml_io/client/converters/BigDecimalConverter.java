package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class BigDecimalConverter implements I_Converter<BigDecimal> {

	@Override
	public BigDecimal fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return new BigDecimal(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, BigDecimal p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.BIG_DECIMAL_TAG, 
					p.toString());
	}

}
