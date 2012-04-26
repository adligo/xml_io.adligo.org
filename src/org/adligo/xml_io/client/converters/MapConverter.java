package org.adligo.xml_io.client.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.i.util.client.I_Iterator;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

@SuppressWarnings("rawtypes")
public class MapConverter implements I_Converter<Map>{

	@SuppressWarnings("unchecked")
	@Override
	public Map fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		I_Iterator it =  info.getChildren();
		
		Map toRet = new HashMap();
		while (it.hasNext()) {
			TagInfo keyValueInfo = (TagInfo) it.next();
			
			I_Iterator kvit = keyValueInfo.getChildren();
			while (kvit.hasNext()) {
				TagInfo keyInfo = (TagInfo) kvit.next();
				String keyXml = Parser.substring(xml, keyInfo);
				Object key = context.readXml(keyXml);
				
				TagInfo valueInfo = (TagInfo) kvit.next();
				String valueXml = Parser.substring(xml, valueInfo);
				Object value = context.readXml(valueXml);
				
				toRet.put(key, value);
			}
		}
		return toRet;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void toXml(Map p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		Set<Entry> entries = p.entrySet();
		builder.indent();
		builder.appendTagHeaderStart(Tags.MAP);
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		
		for (Entry e: entries) {
			builder.indent();
			builder.appendTagHeaderStart(Tags.KEY_VALUE);
			builder.appendTagHeaderEnd(true);
				//indent the key and value 
				builder.addIndentLevel();
				
				builder.indent();
				Object key = e.getKey();
				context.writeXml(key);
				
				builder.indent();
				Object value = e.getValue();
				context.writeXml(value);
				
				builder.removeIndentLevel();
				
			builder.indent();	
			builder.appendEndTag(Tags.KEY_VALUE);
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Tags.MAP);
	}

}
