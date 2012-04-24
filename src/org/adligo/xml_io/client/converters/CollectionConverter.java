package org.adligo.xml_io.client.converters;

import java.util.ArrayList;
import java.util.Collection;

import org.adligo.i.util.client.I_Iterator;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class CollectionConverter implements I_Converter<Collection<?>> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<?> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		
		@SuppressWarnings("rawtypes")
		ArrayList toRet = new ArrayList();
		I_Iterator it = info.getChildren();
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
			Object obj = context.readXml(subXml);
			toRet.add(obj);
		}
		return toRet;
	}

	@Override
	public void toXml(I_XMLBuilder builder, Collection<? extends Object> p,
			Xml_IOWriterContext context) {
		
		builder.appendStartTag(ClassMappings.LIST_TAG);
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		
		for (Object o: p) {
			builder.indent();
			context.writeXml(o);
		}
		builder.removeIndentLevel();
		builder.appendEndTag(ClassMappings.LIST_TAG);
	}



}
