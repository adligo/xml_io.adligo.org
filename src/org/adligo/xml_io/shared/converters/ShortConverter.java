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

public class ShortConverter implements I_Converter<Short>, I_AttributeConverter<Short> {

	@Override
	public ObjectFromXml<Short> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Short>(Short.parseShort(text));
	}

	@Override
	public void toXml(Short p,Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.SHORT_TAG_SUFFIX, "" + p);
	}

	@Override
	public Short fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Short.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Short p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
