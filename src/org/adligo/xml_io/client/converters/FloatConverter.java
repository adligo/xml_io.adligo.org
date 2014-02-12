package org.adligo.xml_io.client.converters;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class FloatConverter implements I_Converter<Float>, I_AttributeConverter<Float> {

	@Override
	public ObjectFromXml<Float> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Float>(Float.parseFloat(text));
	}

	@Override
	public void toXml(Float p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.FLOAT_TAG_SUFFIX, "" + p);
	}

	@Override
	public Float fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		
		return Float.valueOf(attributeValue);
	}

	@Override
	public void toXmlAttribute(Float p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
