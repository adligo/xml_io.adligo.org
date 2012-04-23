package org.adligo.xml_io.client.converters;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.TagInfo;
import org.adligo.xml_io.client.XmlIOSettings;

public interface I_Converter<T> {
	/**
	 * note since null values are omitted on writing to xml
	 * they can be simply assumed to be null if no tag exists for the
	 * field in question.
	 * 
	 * @param xml
	 * @param info
	 * @param settings
	 * @return
	 */
	public T fromXml(String xml, TagInfo info, XmlIOSettings settings); 
	/**
	 * note null checks are not necessary in implementations of this
	 * mehtod, as nulls will simply be omitted from the xml.
	 * @param builder
	 * @param p
	 * @param settings
	 */
	public void toXml(I_XMLBuilder builder, T p, XmlIOSettings settings);
}
