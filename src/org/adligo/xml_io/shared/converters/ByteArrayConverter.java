package org.adligo.xml_io.shared.converters;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.shared.I_AttributeConverter;
import org.adligo.xml_io.shared.I_Converter;
import org.adligo.xml_io.shared.ObjectFromXml;
import org.adligo.xml_io.shared.Xml_IOConstants;
import org.adligo.xml_io.shared.Xml_IOReaderContext;
import org.adligo.xml_io.shared.Xml_IOWriterContext;

public class ByteArrayConverter implements I_Converter<byte []>, I_AttributeConverter<byte []> {

	@Override
	public ObjectFromXml<byte []> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		byte [] bytes = Parser.parseBytes(text);
		return new ObjectFromXml<byte []>(bytes);
	}

	@Override
	public void toXml(byte [] instance, Xml_IOWriterContext context) {
		context.appendDefaultTagBase64(Xml_IOConstants.BYTE_ARRAY_TAG_SUFFIX, instance);
	}

	@Override
	public byte[] fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		byte [] toRet = Parser.parseBytes(attributeValue);
		return toRet;
	}

	@Override
	public void toXmlAttribute(byte[] p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}


}
