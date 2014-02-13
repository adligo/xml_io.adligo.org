package org.adligo.xml_io.shared.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.adligo.i.util.shared.DateTime;
import org.adligo.i.util.shared.StringUtils;
import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.Parser;
import org.adligo.models.params.shared.TagInfo;
import org.adligo.xml_io.shared.I_AttributeConverter;
import org.adligo.xml_io.shared.I_Converter;
import org.adligo.xml_io.shared.ObjectFromXml;
import org.adligo.xml_io.shared.Xml_IOConstants;
import org.adligo.xml_io.shared.Xml_IOReaderContext;
import org.adligo.xml_io.shared.Xml_IOWriterContext;

public class LongConverter implements I_Converter<Long>, I_AttributeConverter<Long> {

	@Override
	public ObjectFromXml<Long> fromXml(String xml, TagInfo info, Xml_IOReaderContext context) {
		String text = Parser.getTextContent(xml, info);
		return new ObjectFromXml<Long>(Long.parseLong(text));
	}

	@Override
	public void toXml(Long p, Xml_IOWriterContext context) {
		context.appendDefaultTag(Xml_IOConstants.LONG_TAG_SUFFIX, "" + p);
	}

	@Override
	public Long fromXmlAttribute(String attributeValue,
			Xml_IOReaderContext context) {
		//as a nicety parse dates into longs using the default DateTime format
		NumberFormatException caught = null;
		Long toRet = null;
		if (StringUtils.isEmpty(attributeValue)) {
			return null;
		}
		try {
			toRet = Long.valueOf(attributeValue);
		} catch (NumberFormatException nfe) {
			caught = nfe;
			DateTime dt = new DateTime(attributeValue);
			toRet = dt.getTime();
		}
		if (toRet == null) {
			throw caught;
		}
		return toRet;
	}

	@Override
	public void toXmlAttribute(Long p, Xml_IOWriterContext context) {
		I_XMLBuilder builder = context.getBuilder();
		String attributeName = context.getNextTagNameAttribute();
		builder.appendAttribute(attributeName, p);
	}
}
