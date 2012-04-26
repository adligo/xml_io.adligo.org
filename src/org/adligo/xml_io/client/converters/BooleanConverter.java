package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BooleanConverter implements I_Converter<Boolean> {

	@Override
	public ObjectFromXml<Boolean> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		if ("t".equals(text)){
			return new ObjectFromXml<Boolean>(true);
		}
		return new ObjectFromXml<Boolean>(false);
	}

	@Override
	public void toXml(Boolean p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		if (p) {
			builder.appendTagWithTextContent(Tags.BOOLEAN, "t");
		} else {
			builder.appendTagWithTextContent(Tags.BOOLEAN, "f");
		}
	}

}
