package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

import org.adligo.xml_io.client.NamespaceConverters;
import org.adligo.xml_io.client.NamespaceConvertersMutant;
import org.adligo.xml_io.client.Xml_IOConstants;

public class DefaultNamespaceConverters {
	public static final Class<?> BYTE_ARRAY_CLASS = (new byte[] {}).getClass();
	public static final Class<?> BOOLEAN_ARRAY_CLASS = (new boolean[] {}).getClass();
	public static final Class<?> CHAR_ARRAY_CLASS = (new char[] {}).getClass();
	public static final NamespaceConverters DEFAULT = getDefaultNamespaceConverters();
	
	private static NamespaceConverters getDefaultNamespaceConverters() {
		NamespaceConvertersMutant converters = new NamespaceConvertersMutant();
		converters.setNamespace(Xml_IOConstants.DEFAULT_NAMESPACE);
		converters.setPackageName(Xml_IOConstants.DEFAULT_PACKAGE);
		
		MapConverter mapConverter = new MapConverter();
		CollectionConverter collectionConverter = new CollectionConverter();
		
		converters.addObjectToXmlConverter(Boolean.class, new BooleanConverter());
		converters.addObjectToXmlConverter(boolean.class, new BooleanConverter());
		converters.addObjectToXmlConverter(Byte.class, new ByteConverter());
		converters.addObjectToXmlConverter(byte.class, new ByteConverter());
		
		converters.addObjectToXmlConverter(Character.class, new CharacterConverter());
		converters.addObjectToXmlConverter(char.class, new CharacterConverter());
		converters.addObjectToXmlConverter(Double.class, new DoubleConverter());
		converters.addObjectToXmlConverter(double.class, new DoubleConverter());
		converters.addObjectToXmlConverter(BigDecimal.class, new BigDecimalConverter());
		
		converters.addObjectToXmlConverter(Float.class, new FloatConverter());
		converters.addObjectToXmlConverter(float.class, new FloatConverter());
		
		converters.addObjectToXmlConverter(Map.class, mapConverter);
		converters.addObjectToXmlConverter(Collection.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Integer.class, new IntegerConverter());
		converters.addObjectToXmlConverter(int.class, new IntegerConverter());
		converters.addObjectToXmlConverter(BigInteger.class, new BigIntegerConverter());
		
		converters.addObjectToXmlConverter(Long.class, new LongConverter());
		converters.addObjectToXmlConverter(long.class, new LongConverter());
		
		converters.addObjectToXmlConverter(Short.class, new ShortConverter());
		converters.addObjectToXmlConverter(short.class, new ShortConverter());
		converters.addObjectToXmlConverter(String.class, new StringConverter());

		
		converters.addObjectToXmlConverter(BYTE_ARRAY_CLASS, new ByteArrayConverter());
		converters.addObjectToXmlConverter(BOOLEAN_ARRAY_CLASS, new BooleanArrayConverter());
		converters.addObjectToXmlConverter(CHAR_ARRAY_CLASS, new CharArrayConverter());
		
		addNodeConverters(converters);
		
		addAttributeConverters(converters);
		
		return new NamespaceConverters(converters);
	}

	private static void addNodeConverters(NamespaceConvertersMutant converters) {
		MapConverter mapConverter = new MapConverter();
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BOOLEAN_TAG_SUFFIX, new BooleanConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BYTE_TAG_SUFFIX, new ByteConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.CHARACTER_TAG_SUFFIX, new CharacterConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.DOUBLE_TAG_SUFFIX, new DoubleConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BIG_DECIMAL_TAG_SUFFIX, new BigDecimalConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.FLOAT_TAG_SUFFIX, new FloatConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.INTEGER_TAG_SUFFIX, new IntegerConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BIG_INTEGER_TAG_SUFFIX, new BigIntegerConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.LONG_TAG_SUFFIX, new LongConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.SHORT_TAG_SUFFIX, new ShortConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.STRING_TAG_SUFFIX, new StringConverter());
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.LIST_TAG_SUFFIX, new CollectionConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.MAP_TAG_SUFFIX, mapConverter);
		
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BYTE_ARRAY_TAG_SUFFIX, new ByteArrayConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.BOOlEAN_ARRAY_TAG_SUFFIX, new BooleanArrayConverter());
		converters.addXmlToObjectConverter(
				Xml_IOConstants.CHAR_ARRAY_TAG_SUFFIX, new CharArrayConverter());
	}

	private static void addAttributeConverters(NamespaceConvertersMutant converters) {
		converters.addXmlAttributeToObjectConverter(
				Boolean.class, new BooleanConverter());
		converters.addXmlAttributeToObjectConverter(
				boolean.class, new BooleanConverter());
		converters.addXmlAttributeToObjectConverter(
				Byte.class, new ByteConverter());
		converters.addXmlAttributeToObjectConverter(
				byte.class, new ByteConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Character.class, new CharacterConverter());
		converters.addXmlAttributeToObjectConverter(
				char.class, new CharacterConverter());
		converters.addXmlAttributeToObjectConverter(
				Double.class, new DoubleConverter());
		converters.addXmlAttributeToObjectConverter(
				double.class, new DoubleConverter());
		converters.addXmlAttributeToObjectConverter(
				BigDecimal.class, new BigDecimalConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Float.class, new FloatConverter());
		converters.addXmlAttributeToObjectConverter(
				float.class, new FloatConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Integer.class, new IntegerConverter());
		converters.addXmlAttributeToObjectConverter(
				int.class, new IntegerConverter());
		converters.addXmlAttributeToObjectConverter(
				BigInteger.class, new BigIntegerConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Long.class, new LongConverter());
		converters.addXmlAttributeToObjectConverter(
				long.class, new LongConverter());
		converters.addXmlAttributeToObjectConverter(
				Short.class, new ShortConverter());
		converters.addXmlAttributeToObjectConverter(
				short.class, new ShortConverter());
		converters.addXmlAttributeToObjectConverter(
				String.class, new StringConverter());
		
		
		converters.addXmlAttributeToObjectConverter(
				BYTE_ARRAY_CLASS, new ByteArrayConverter());
		converters.addXmlAttributeToObjectConverter(
				BOOLEAN_ARRAY_CLASS, new BooleanArrayConverter());
		converters.addXmlAttributeToObjectConverter(
				CHAR_ARRAY_CLASS, new CharArrayConverter());
	}
}
