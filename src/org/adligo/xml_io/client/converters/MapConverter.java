package org.adligo.xml_io.client.converters;

import java.util.Map;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOReaderContext;
import org.adligo.xml_io.client.Xml_IOWriterContext;

public class MapConverter implements I_Converter<Map<?,?>>{

	@Override
	public Map<?, ?> fromXml(String xml, TagInfo info,
			Xml_IOReaderContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toXml(I_XMLBuilder builder, Map<?, ?> p,
			Xml_IOWriterContext context) {
		// TODO Auto-generated method stub
		
	}

}
