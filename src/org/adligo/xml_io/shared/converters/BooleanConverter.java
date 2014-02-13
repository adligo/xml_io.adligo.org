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
		if (p) {
			context.appendDefaultTag(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, "t");
		} else {
			context.appendDefaultTag(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, "f");
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
