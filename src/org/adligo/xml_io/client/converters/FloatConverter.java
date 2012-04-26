package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class FloatConverter implements I_Converter<Float> {

	@Override
	public ObjectFromXml<Float> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Float>(Float.parseFloat(text));
	}

	@Override
	public void toXml(Float p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagWithTextContent(Tags.FLOAT, "" + p);
	}

}
