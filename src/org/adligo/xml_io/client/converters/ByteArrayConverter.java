package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class ByteArrayConverter implements I_Converter<byte []>{

	@Override
	public byte [] fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		byte [] bytes = Parser.parseBytes(text);
		return bytes;
	}

	@Override
	public void toXml(byte [] instance, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		builder.appendTagHeaderStart(Tags.BYTE_ARRAY);
		builder.appendTagHeaderEnd(false);
		builder.appendBase64(instance);
		builder.appendEndTag(Tags.BYTE_ARRAY);
	}


}
