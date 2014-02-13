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

public class IntegerConverter implements I_Converter<Integer>, I_AttributeConverter<Integer> {

	@Override
	public ObjectFromXml<Integer> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Integer>(Integer.parseInt(text));
	}

	@Override
	public void toXml(Integer p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.INTEGER_TAG_SUFFIX, "" + p);
	}

	@Override
	public Integer fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Integer.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Integer p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
