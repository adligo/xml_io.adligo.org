package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BooleanConverter implements I_Converter<Boolean>, I_AttributeConverter<Boolean> {

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
			builder.appendTagWithTextContent(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, "t");
		} else {
			builder.appendTagWithTextContent(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, "f");
		}
	}

	@Override
	public Boolean fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		if ("t".equals(attributeValue)) {
			return true;
		}
		return false;
	}

	@Override
	public void toXmlAttribute(Boolean p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		if (p) {
			builder.appendAttribute(attributeName, "t");
		} else {
			builder.appendAttribute(attributeName, "f");
		}
	}

}
