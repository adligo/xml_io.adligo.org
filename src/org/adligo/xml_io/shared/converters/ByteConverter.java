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

public class ByteConverter implements I_Converter<Byte> , I_AttributeConverter<Byte>{

	@Override
	public ObjectFromXml<Byte> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		byte [] bytes = Parser.parseBytes(text);
		return new ObjectFromXml<Byte>(bytes[0]);
	}

	@Override
	public void toXml(Byte instance, Xml_IOWriterContext context) {
		context.appendDefaultTagBase64(Xml_IOConstants.BYTE_TAG_SUFFIX, new byte[] {instance.byteValue()});
	}

	@Override
	public Byte fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		byte [] toRet = Parser.parseBytes(attributeValue);
		return toRet[0];
	}

	@Override
	public void toXmlAttribute(Byte p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, new byte[]{p});
	}

}
