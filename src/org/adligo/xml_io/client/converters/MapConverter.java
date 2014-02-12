package org.adligo.xml_io.client.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.i.util.shared.I_Iterator;
import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagAttribute;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOConstants;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

@SuppressWarnings("rawtypes")
public class MapConverter implements I_Converter<Map>{

	@SuppressWarnings("unchecked")
	@Override
	public ObjectFromXml<Map> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		I_Iterator it = Parser.getAttributes(info, xml);
		String name = null;
		while (it.hasNext()) {
			TagAttribute attrib = (TagAttribute) it.next();
			String key = attrib.getName();
			if (Xml_IOConstants.N_NAME_ATTRIBUTE.equals(key)) {
				name = attrib.getValue();
				break;
			}
		}
		
		it =  info.getChildren();
		
		Map toRet = new HashMap();
		while (it.hasNext()) {
			TagInfo keyValueInfo = (TagInfo) it.next();
			
			I_Iterator kvit = keyValueInfo.getChildren();
			while (kvit.hasNext()) {
				TagInfo keyInfo = (TagInfo) kvit.next();
				String keyXml = Parser.substring(xml, keyInfo);
				ObjectFromXml<?> keyObj = context.readXml(keyXml);
				
				TagInfo valueInfo = (TagInfo) kvit.next();
				String valueXml = Parser.substring(xml, valueInfo);
				ObjectFromXml<?> valueObj = context.readXml(valueXml);
				
				Object key = keyObj.getValue();
				Object value = valueObj.getValue();
				toRet.put(key, value);
			}
		}
		if (name != null) {
			return new ObjectFromXml<Map>(name, toRet);
		}
		return new ObjectFromXml<Map>(toRet);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void toXml(Map p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		Set<Entry> entries = p.entrySet();
		context.appendTagHeaderStart(Xml_IOConstants.MAP_TAG_SUFFIX);
		context.appendSchemaInfoToFirstTag();
		
		String nameValue = context.getNextTagNameAttribute();
		if (nameValue != null) {
			builder.appendAttribute(Xml_IOConstants.N_NAME_ATTRIBUTE, nameValue);
			//clear this for child objects
			context.setNextTagNameAttribute(null);
		}
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		
		for (Entry e: entries) {
			context.appendTagHeaderStart(Xml_IOConstants.KEY_VALUE_TAG_SUFFIX);
			builder.appendTagHeaderEnd(true);
				//indent the key and value 
				builder.addIndentLevel();
				
				Object key = e.getKey();
				context.writeXml(key);
				
				Object value = e.getValue();
				context.writeXml(value);
				
				builder.removeIndentLevel();
				
			context.appendEndTag(Xml_IOConstants.KEY_VALUE_TAG_SUFFIX);
		}
		builder.removeIndentLevel();
		context.appendEndTag(Xml_IOConstants.MAP_TAG_SUFFIX);
	}

}
