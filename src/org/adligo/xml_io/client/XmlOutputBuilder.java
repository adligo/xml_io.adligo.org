package org.adligo.xml_io.client;

import java.util.Vector;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;

public class XmlOutputBuilder {
	public String writeXml(Object p) {
		return writeXml(p, new XmlIOSettings());
	}
	
	public String writeXml(Object p, XmlIOSettings settings) {
		if (p == null) {
			return "";
		}
		Class<?> clazz = p.getClass();
		I_XMLBuilder builder = settings.getBuilder();
		
		String tagName = ClassMappings.CLASS_TO_CHAR.get(clazz);
		
		if (ClassUtils.typeOf(p, Vector.class)) {
			
		} else if (ClassMappings.COLLECTION_CLASSES.contains(clazz)) {
			
		} else if (ClassMappings.MAP_CLASSES.contains(clazz)) {
			
		} else if (tagName != null){
			if (ClassUtils.typeOf(p, String.class)) {
				appendSimple(builder, (String) p, tagName);
			} else if (ClassUtils.typeOf(p, Boolean.class)){
				appendSimple(builder, (Boolean) p, tagName);
			} else if (ClassUtils.typeOf(p, Byte.class)){
				appendSimple(builder, (Byte) p);
			} else {
				appendSimple(builder, p, tagName);
			}
		} else {
			//do custom class
		}
		return builder.toXmlString();
	}

	private void appendSimple(I_XMLBuilder builder, String p, String tagName) {
		builder.appendStartTag(tagName);
		builder.appendTagHeaderEnd(false);
		String text = p.toString();
		String escapedText = Parser.escapeForXml(text);
		builder.append(escapedText);
		builder.appendEndTag(tagName);
	}
	
	private void appendSimple(I_XMLBuilder builder, Object p, String tagName) {
		builder.appendStartTag(tagName);
		builder.appendTagHeaderEnd(false);
		builder.append(p.toString());
		builder.appendEndTag(tagName);
	}
	
	private void appendSimple(I_XMLBuilder builder, Boolean p, String tagName) {
		builder.appendStartTag(tagName);
		builder.appendTagHeaderEnd(false);
		if (p) {
			builder.append("t");
		} else {
			builder.append("f");
		}
		builder.appendEndTag(tagName);
	}
	
	private void appendSimple(I_XMLBuilder builder, Byte p) {
		if (p != null) {
			appendSimple(builder, new byte[] {p.byteValue()});
		} else {
			appendSimple(builder, (byte []) null);
		}
	}
	
	private void appendSimple(I_XMLBuilder builder, byte [] p) {
		builder.appendStartTag(ClassMappings.BYTE_TAG);
		builder.appendTagHeaderEnd(false);
		if (p != null) {
			builder.appendBase64(p);
		}
		builder.appendEndTag(ClassMappings.BYTE_TAG);
	}
}
