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
		int header = xml.indexOf(Xml_IOConstants.HEADER);
		if (header != -1) {
			xml = xml.substring(Xml_IOConstants.HEADER.length(), xml.length() );
		}
		TagInfo info = Parser.getNextTagInfo(xml,0);
		String tagName = info.getTagName();
		
		Xml_IOReaderContext context = new Xml_IOReaderContext();
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
