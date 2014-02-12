package org.adligo.xml_io.client;

import org.adligo.models.params.shared.I_XMLBuilder;

/**
 * this class should not be considered thread safe one per thread
 * @author scott
 *
 */
public class Xml_IOWriter {

	
	public String writeXml( Object p) {
		Xml_IOSettingsMutant set = new Xml_IOSettingsMutant();
		set.setConfig(new NamespacePrefixConfig());
		return writeXml(p, set);
	}
	
	public String writeXml(Object p, I_Xml_IOSettings settings) {
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
		String indent = settings.getIndent();
		if (indent != null) {
			builder.setIndent(indent);
		}
		String linefeed = settings.getLinefeed();
		if (linefeed != null) {
			builder.setLineFeed("");
		}
		context.writeXml(p);
		
		return builder.toXmlString();
	}
	
	
}
