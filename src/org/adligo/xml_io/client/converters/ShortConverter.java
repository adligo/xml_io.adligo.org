package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class ShortConverter implements I_Converter<Short> {

	@Override
	public Short fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return Short.parseShort(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Short p,Xml_IOWriterContext context) {
		builder.appendTagWithTextContent(ClassMappings.SHORT_TAG, "" + p);
	}

}
