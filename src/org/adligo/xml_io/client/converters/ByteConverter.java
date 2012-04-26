package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class ByteConverter implements I_Converter<Byte>{

	@Override
	public Byte fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		byte [] bytes = Parser.parseBytes(text);
		return bytes[0];
	}

	@Override
	public void toXml(Byte instance, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagHeaderStart(Tags.BYTE);
		builder.appendTagHeaderEnd(false);
		if (instance != null) {
			builder.appendBase64(new byte[] {instance.byteValue()});
		}
		builder.appendEndTag(Tags.BYTE);
	}

}
