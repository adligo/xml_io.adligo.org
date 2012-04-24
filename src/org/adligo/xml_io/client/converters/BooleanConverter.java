package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BooleanConverter implements I_Converter<Boolean> {

	@Override
	public Boolean fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		if ("t".equals(text)){
			return true;
		}
		return false;
	}

	@Override
	public void toXml(I_XMLBuilder builder, Boolean p, Xml_IOWriterContext context) {
		if (p) {
			builder.appendTagWithTextContent(ClassMappings.BOOLEAN_TAG, "t");
		} else {
			builder.appendTagWithTextContent(ClassMappings.BOOLEAN_TAG, "f");
		}
	}

}
