package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class ByteConverter implements I_Converter<Byte>{

	@Override
	public Byte fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		byte [] bytes = Parser.parseBytes(text);
		return bytes[0];
	}

	@Override
	public void toXml(I_XMLBuilder builder, Byte instance, XmlIOSettings settings) {
		builder.appendStartTag(ClassMappings.BYTE_TAG);
		builder.appendTagHeaderEnd(false);
		if (instance != null) {
			builder.appendBase64(new byte[] {instance.byteValue()});
		}
		builder.appendEndTag(ClassMappings.BYTE_TAG);
	}

}
