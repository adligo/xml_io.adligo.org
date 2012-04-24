package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class LongConverter implements I_Converter<Long> {

	@Override
	public Long fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return Long.parseLong(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Long p, Xml_IOWriterContext context) {
		builder.appendTagWithTextContent(ClassMappings.LONG_TAG, "" + p);
	}

}
