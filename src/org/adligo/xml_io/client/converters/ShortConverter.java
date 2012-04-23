package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class ShortConverter implements I_Converter<Short> {

	@Override
	public Short fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		return Short.parseShort(text);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Short p, XmlIOSettings settings) {
		builder.appendTagWithTextContent(ClassMappings.SHORT_TAG, "" + p);
	}

}
