package org.adligo.xml_io.client;

import java.io.UnsupportedEncodingException;

import org.adligo.models.params.client.I_XMLBuilder;

public class Xml_IOWriter {

	
	public String writeXml( Object p) {
		return writeXml(p, new Xml_IOSettings());
	}
	
	public String writeXml(Object p, Xml_IOSettings settings) {
		if (p == null) {
			return "";
		}

		Xml_IOWriterContext context = new Xml_IOWriterContext();
		context.setSettings(settings);
		context.setWriter(this);
		I_XMLBuilder builder = context.getBuilder();
		if (settings.isIncludeXmlHeader()) {
			builder.append(Xml_IOConstants.HEADER);
			builder.lineFeed();
		}
		context.writeXml(p);
		
		return builder.toXmlString();
	}
	
	
}
