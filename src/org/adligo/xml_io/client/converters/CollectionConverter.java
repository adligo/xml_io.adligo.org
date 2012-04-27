package org.adligo.xml_io.client.converters;

import java.util.ArrayList;
import java.util.Collection;

import org.adligo.i.util.client.I_Iterator;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.Parser;
import org.adligo.models.params.client.TagAttribute;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class CollectionConverter implements I_Converter<Collection<?>> {

	@SuppressWarnings("unchecked")
	@Override
	public ObjectFromXml<Collection<?>> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		@SuppressWarnings("rawtypes")
		ArrayList toRet = new ArrayList();
		
		I_Iterator it = Parser.getAttributes(info, xml);
		String name = null;
		while (it.hasNext()) {
			TagAttribute attrib = (TagAttribute) it.next();
			String key = attrib.getName();
			if (Tags.NAME_ATTRIBUTE.equals(key)) {
				name = attrib.getValue();
				break;
			}
		}
		
		it = info.getChildren();
		while (it.hasNext()) {
			TagInfo childInfo = (TagInfo) it.next();
			int start = 0;
			int end = 0;
			if (childInfo.hasEnder()) {
				start = childInfo.getHeaderStart();
				end = childInfo.getEnderEnd() + 1;
			} else {
				start = childInfo.getHeaderStart();
				end = childInfo.getHeaderEnd() + 1;
			}
			String subXml = xml.substring(start, end);
			ObjectFromXml<?> obj = context.readXml(subXml);
			Object val = obj.getValue();
			toRet.add(val);
		}
		if (name != null) {
			return new ObjectFromXml<Collection<?>>(name, toRet);
		}
		return new ObjectFromXml<Collection<?>>(toRet);
	}

	@Override
	public void toXml(Collection<? extends Object> p, Xml_IOWriterContext context) {
		
		I_XMLBuilder builder = context.getBuilder();
		builder.indent();
		builder.appendTagHeaderStart(Tags.LIST);
		
		String nameValue = context.getNextTagNameAttribute();
		if (nameValue != null) {
			builder.appendAttribute(Tags.NAME_ATTRIBUTE, nameValue);
			//clear this for child objects
			context.setNextTagNameAttribute(null);
		}
		
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		
		for (Object o: p) {
			builder.indent();
			context.writeXml(o);
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Tags.LIST);
	}



}
