package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class BooleanArrayConverter implements I_Converter<boolean []>{

	@Override
	public boolean[] fromXml(String xml, TagInfo info,
			Xml_IOReaderContext context) {
		
		String text = Parser.getTextContent(xml, info);
		char [] chars = text.toCharArray();
		boolean [] toRet = new boolean[chars.length];
		for (int i = 0; i < toRet.length; i++) {
			char c = chars[i];
			if ('t' == c) {
				toRet[i] = true; 
			} else {
				toRet[i] = false;
			}
		}
		return toRet;
	}

	@Override
	public void toXml(I_XMLBuilder builder, boolean[] p,
			Xml_IOWriterContext context) {
		
		builder.appendStartTag(ClassMappings.BOOlEAN_ARRAY_TAG);
		builder.appendTagHeaderEnd(false);
		for (int i = 0; i < p.length; i++) {
			boolean b = p[i];
			if (b) {
				builder.append("t");
			} else {
				builder.append("f");
			}
		}
		builder.appendEndTag(ClassMappings.BOOlEAN_ARRAY_TAG);
	}

}
