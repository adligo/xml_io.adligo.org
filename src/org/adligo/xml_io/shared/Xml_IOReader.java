package org.adligo.xml_io.shared;

import org.adligo.i.util.shared.I_Iterator;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagAttribute;
import org.adligo.models.params.shared.TagInfo;


public class Xml_IOReader {


	public Object readXml(String p) {
		Xml_IOSettingsMutant set = new Xml_IOSettingsMutant();
		set.setConfig(new NamespacePrefixConfig());
		return readXml(p, set);
	}
	
	public Object readXml(String xml, Xml_IOSettingsMutant settings) {
		xml = Parser.stripComments(xml);
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		if ("?xml".equals(tagName)) {
			int start = info.getHeaderEnd() + 1;
			xml = xml.substring(start, xml.length());
		}
		info = Parser.getNextTagInfo(xml,0);
		
		
		Xml_IOReaderContext context = new Xml_IOReaderContext();
		xml = dealWithNamesapce(xml, info, context, settings);
		info = Parser.getNextTagInfo(xml, 0);
		tagName = info.getTagName();
		
		context.setSettings(settings);
		
		Xml_IOTagContext tagCtx = context.getTagContext(tagName);
		I_Converter<?> converter = settings.getFromXmlConverter(tagCtx);
		if (converter == null) {
			throw new IllegalArgumentException("Could not find a converter for tag '" 
					+ tagName + "'");
		}
		
		context.setReader(this);
		
		ObjectFromXml<?> result = converter.fromXml(xml, info, context);
		return result.getValue();
	}

	private String dealWithNamesapce(String xml, TagInfo info, Xml_IOReaderContext ctx, I_Xml_IOSettings settings) {
		StringBuilder sb = new StringBuilder();
		I_Iterator it =  Parser.getAttributes(info, xml);
		int headerStart = info.getHeaderStart();
		String beforeTag = xml.substring(0, headerStart);
		sb.append(beforeTag);
		String tagName = info.getTagName();
		sb.append("<");
		sb.append(tagName);
		while (it.hasNext()) {
			TagAttribute ta = (TagAttribute) it.next();
			String name = ta.getName();
			boolean nsTag = false;
			if (!settings.isIgnoreFileNamespace()) {
				if (name.toUpperCase().contains("XMLNS")) {
					nsTag = true;
					String ns = ta.getValue();
					
					String prefix = "";
					int colon = name.indexOf(":");
					if (colon != -1) {
						if (colon + 1 <= name.length()) {
							prefix = name.substring(colon + 1, name.length());
						}
					}
					ctx.addNamespace(prefix, ns);
				}
			}
			if (!nsTag) {
				sb.append(" ");
				sb.append(name);
				sb.append("\"");
				String val = ta.getValue();
				sb.append(val);
				sb.append("\"");
			}
		}
		if (!info.hasEnder()) {
			sb.append("/>");
		} else {
			int ender = info.getHeaderEnd();
			String theRest = xml.substring(ender, xml.length());
			sb.append(theRest);
		}
		
		
		return sb.toString();
	}


	
}
