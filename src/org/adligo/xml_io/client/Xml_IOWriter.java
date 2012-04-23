package org.adligo.xml_io.client;

import java.util.Vector;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.xml_io.client.converters.ClassMappings;
import org.adligo.xml_io.client.converters.I_Converter;

public class Xml_IOWriter {
	public String writeXml(Object p) {
		return writeXml(p, new XmlIOSettings());
	}
	
	public String writeXml(Object p, XmlIOSettings settings) {
		if (p == null) {
			return "";
		}
		Class<?> clazz = p.getClass();
		I_Converter<Object> converter = (I_Converter<Object>) 
					settings.getToXmlConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException("Could not find a converter for class " 
					+ clazz);
		}
		I_XMLBuilder builder = settings.getBuilder();
		converter.toXml(builder, p, settings);
		return builder.toXmlString();
	}
}
