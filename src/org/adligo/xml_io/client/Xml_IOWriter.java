package org.adligo.xml_io.client;

import org.adligo.models.params.client.I_XMLBuilder;

public class Xml_IOWriter {
	public String writeXml(Object p) {
		return writeXml(p, new Xml_IOSettings());
	}
	
	public String writeXml(Object p, Xml_IOSettings settings) {
		if (p == null) {
			return "";
		}

		Xml_IOWriterContext context = new Xml_IOWriterContext();
		context.setSettings(settings);
		context.setWriter(this);
		context.writeXml(p);
		
		I_XMLBuilder builder = context.getBuilder();
		return builder.toXmlString();
	}
	
	
}
