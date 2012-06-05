package org.adligo.xml_io.client;

import org.adligo.i.util.client.I_Iterator;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagAttribute;
import org.adligo.models.params.client.TagInfo;


public class Xml_IOReader {


	public Object readXml(String xml) {
		return readXml(xml, new Xml_IOSettings());
	}
	
	public Object readXml(String xml, Xml_IOSettings settings) {
		xml = Parser.stripComments(xml);
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		if ("?xml".equals(tagName)) {
			int start = info.getHeaderEnd() + 1;
			xml = xml.substring(start, xml.length());
		}
		info = Parser.getNextTagInfo(xml,0);
		
		
		Xml_IOReaderContext context = new Xml_IOReaderContext();
		xml = dealWithNamesapce(xml, info, settings);
		info = Parser.getNextTagInfo(xml, 0);
		tagName = info.getTagName();
		
		if (settings.getConfig() == null) {
			setUpConfig(xml, settings, info);
		}
		context.setSettings(settings);
		I_Converter<?> converter = settings.getFromXmlConverter(tagName);
		if (converter == null) {
			throw new IllegalArgumentException("Could not find a converter for tag '" 
					+ tagName + "'");
		}
		
		context.setReader(this);
		
		ObjectFromXml<?> result = converter.fromXml(xml, info, context);
		return result.getValue();
	}

	private String dealWithNamesapce(String xml, TagInfo info, Xml_IOSettings settings) {
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
			if (name.toUpperCase().contains("XMLNS")) {
				//TODO deal with namespace, in settings by mutating settings?
			} else {
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

	private void setUpConfig(String xml, Xml_IOSettings settings, TagInfo info) {
		I_Iterator tagsIt = Parser.getAttributes(info, xml);
		while (tagsIt.hasNext()) {
			TagAttribute attribute = (TagAttribute) tagsIt.next();
			String name = attribute.getName();
			int index = name.indexOf(Xml_IOConstants.XMLNS_ATTRIBUTE);
			if (index != -1) {
				int start = index + Xml_IOConstants.XMLNS_ATTRIBUTE.length() + 1;
				if (start < name.length()) {
					String prefix = name.substring(start, name.length());
					String namespace = attribute.getValue();
					settings.addNamespace(namespace, prefix);
				}
			}
			
		}
		settings.setUpConfig();
	}

	
}
