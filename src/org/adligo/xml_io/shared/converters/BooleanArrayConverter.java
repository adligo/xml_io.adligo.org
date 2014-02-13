package org.adligo.xml_io.shared.converters;

import org.adligo.i.util.shared.AppenderFactory;
import org.adligo.i.util.shared.I_Appender;
import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.shared.I_AttributeConverter;
import org.adligo.xml_io.shared.I_Converter;
import org.adligo.xml_io.shared.ObjectFromXml;
import org.adligo.xml_io.shared.Xml_IOConstants;
import org.adligo.xml_io.shared.Xml_IOReaderContext;
import org.adligo.xml_io.shared.Xml_IOWriterContext;

public class BooleanArrayConverter implements I_Converter<boolean []>, I_AttributeConverter<boolean[]> {

	@Override
	public ObjectFromXml<boolean[]> fromXml(String xml, TagInfo info,
			Xml_IOReaderContext context) {
		
		String text = Parser.getTextContent(xml, info);
		boolean[] toRet = booleanArrayFromString(text);
		return new ObjectFromXml<boolean []>(toRet);
	}

	private boolean[] booleanArrayFromString(String text) {
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
	public void toXml(boolean[] p,Xml_IOWriterContext context) {
		String asString = booleanArrayToString(p);
		context.appendDefaultTag(Xml_IOConstants.BOOlEAN_ARRAY_TAG_SUFFIX, asString);
	}

	private String booleanArrayToString(boolean[] p) {
		I_Appender out = AppenderFactory.create();
		for (int i = 0; i < p.length; i++) {
			boolean b = p[i];
			if (b) {
				out.append("t");
			} else {
				out.append("f");
			}
		}
		return out.toString();
	}

	@Override
	public boolean [] fromXmlAttribute(String attributeValue, Xml_IOReaderContext context) {
		boolean[] toRet = booleanArrayFromString(attributeValue);
		return toRet;
	}

	@Override
	public void toXmlAttribute(boolean [] p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String name = context.getNextTagNameAttribute();
		String asString = booleanArrayToString(p);
		builder.appendAttribute(name, asString);
	}
}
