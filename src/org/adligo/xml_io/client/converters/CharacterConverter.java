package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public class CharacterConverter implements I_Converter<Character>{

	@Override
	public Character fromXml(String xml, TagInfo info, XmlIOSettings settings) {
		String text = Parser.getTextContent(xml, info);
		String result = Parser.unescapeFromXml(text);
		return result.charAt(0);
	}

	@Override
	public void toXml(I_XMLBuilder builder, Character text, XmlIOSettings settings) {
		String escapedText = Parser.escapeForXml("" + text);
		builder.appendTagWithTextContent(ClassMappings.CHARACTER_TAG, escapedText);
	}

}
