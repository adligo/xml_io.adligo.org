package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class DoubleConverter implements I_Converter<Double> {

	@Override
	public Double fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Double.parseDouble(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Double p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.DOUBLE_TAG, "" + p);
	}

}
