package org.adligo.xml_io.client.converters;

import java.util.ArrayList;
import java.util.Collection;

import org.adligo.i.util.shared.I_Iterator;
import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagAttribute;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.ObjectFromXml;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOConstants;
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
			if (Xml_IOConstants.N_NAME_ATTRIBUTE.equals(key)) {
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
		context.appendTagHeaderStart(Xml_IOConstants.LIST_TAG_SUFFIX);
		context.appendSchemaInfoToFirstTag();
		
		String nameValue = context.getNextTagNameAttribute();
		if (nameValue != null) {
			builder.appendAttribute(Xml_IOConstants.N_NAME_ATTRIBUTE, nameValue);
			//clear this for child objects
			context.setNextTagNameAttribute(null);
		}
		
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		
		for (Object o: p) {
			context.writeXml(o);
		}
		builder.removeIndentLevel();
		context.appendEndTag(Xml_IOConstants.LIST_TAG_SUFFIX);
	}



}
