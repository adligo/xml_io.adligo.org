package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class IntegerConverter implements I_Converter<Integer> {

	@Override
	public Integer fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Integer.parseInt(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Integer p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.INTEGER_TAG, "" + p);
	}

}
