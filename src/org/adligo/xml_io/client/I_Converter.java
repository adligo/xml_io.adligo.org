package org.adligo.xml_io.client;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.TagInfo;

public interface I_Converter<T> {
	/**
	 * note since null values are omitted on writing to xml
	 * they can be simply assumed to be null if no tag exists for the
	 * field in question.
	 * 
	 * @param xml
	 * @param info
	 * @param context
	 * @return
	 */
	public ObjectFromXml<T> fromXml(String xml, TagInfo info, Xml_IOReaderContext context); 
	
	/**
	 * note null checks are not necessary in implementations of this
	 * method, as nulls will simply be omitted from the xml.
	 * @param p
	 * @param context
	 */
	public void toXml(T p, Xml_IOWriterContext context);
}
