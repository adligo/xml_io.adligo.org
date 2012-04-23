package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class LongConverter implements I_Converter<Long> {

	@Override
	public Long fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Long.parseLong(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Long p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.LONG_TAG, "" + p);
	}

}
