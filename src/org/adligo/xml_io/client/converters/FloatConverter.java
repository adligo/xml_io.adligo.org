package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class FloatConverter implements I_Converter<Float> {

	@Override
	public Float fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Float.parseFloat(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Float p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.FLOAT_TAG, "" + p);
	}

}
