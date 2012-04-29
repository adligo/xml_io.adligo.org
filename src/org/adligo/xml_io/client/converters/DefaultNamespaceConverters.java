package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.adligo.xml_io.client.NamespaceConverters;
import org.adligo.xml_io.client.Xml_IOConstants;

public class DefaultNamespaceConverters {
	public static final Class<?> BYTE_ARRAY_CLASS = (new byte[] {}).getClass();
	public static final Class<?> BOOLEAN_ARRAY_CLASS = (new boolean[] {}).getClass();
	public static final Class<?> CHAR_ARRAY_CLASS = (new char[] {}).getClass();
	
	
	public static NamespaceConverters getDefaultNamespaceConverters() {
		NamespaceConverters converters = new NamespaceConverters();
		converters.setNamespace(Xml_IOConstants.DEFAULT_NAMESPACE);
		
		MapConverter mapConverter = new MapConverter();
		CollectionConverter collectionConverter = new CollectionConverter();
		converters.addObjectToXmlConverter(
				ArrayList.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Boolean.class, new BooleanConverter());
		converters.addObjectToXmlConverter(Byte.class, new ByteConverter());
		
		converters.addObjectToXmlConverter(Character.class, new CharacterConverter());
		converters.addObjectToXmlConverter(Double.class, new DoubleConverter());
		converters.addObjectToXmlConverter(BigDecimal.class, new BigDecimalConverter());
		
		//converters.addObjectToXmlConverter(MAP, EnumMap.class);
		converters.addObjectToXmlConverter(EnumSet.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Float.class, new FloatConverter());

		converters.addObjectToXmlConverter(HashMap.class, mapConverter);
		converters.addObjectToXmlConverter(HashSet.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Integer.class, new IntegerConverter());
		converters.addObjectToXmlConverter(BigInteger.class, new BigIntegerConverter());
		
		converters.addObjectToXmlConverter(IdentityHashMap.class, mapConverter);
		converters.addObjectToXmlConverter(LinkedHashMap.class, mapConverter);
		
		converters.addObjectToXmlConverter(LinkedHashSet.class, collectionConverter);
		converters.addObjectToXmlConverter(LinkedList.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Long.class, new LongConverter());
		converters.addObjectToXmlConverter(ArrayList.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Stack.class, collectionConverter);
		converters.addObjectToXmlConverter(PriorityQueue.class, collectionConverter);
		
		converters.addObjectToXmlConverter(Short.class, new ShortConverter());
		converters.addObjectToXmlConverter(String.class, new StringConverter());
		
		converters.addObjectToXmlConverter(TreeSet.class, collectionConverter);
		converters.addObjectToXmlConverter(TreeMap.class, mapConverter);
		converters.addObjectToXmlConverter(Vector.class, collectionConverter);
		
		converters.addObjectToXmlConverter(BYTE_ARRAY_CLASS, new ByteArrayConverter());
		converters.addObjectToXmlConverter(BOOLEAN_ARRAY_CLASS, new BooleanArrayConverter());
		converters.addObjectToXmlConverter(CHAR_ARRAY_CLASS, new CharArrayConverter());
		
		addNodeConverters(converters);
		
		addAttributeConverters(converters);
		
		return converters;
	}

	private static void addNodeConverters(NamespaceConverters converters) {
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

	private static void addAttributeConverters(NamespaceConverters converters) {
		converters.addXmlAttributeToObjectConverter(
				Boolean.class, new BooleanConverter());
		converters.addXmlAttributeToObjectConverter(
				Byte.class, new ByteConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Character.class, new CharacterConverter());
		converters.addXmlAttributeToObjectConverter(
				Double.class, new DoubleConverter());
		converters.addXmlAttributeToObjectConverter(
				BigDecimal.class, new BigDecimalConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Float.class, new FloatConverter());

		converters.addXmlAttributeToObjectConverter(
				Integer.class, new IntegerConverter());
		converters.addXmlAttributeToObjectConverter(
				BigInteger.class, new BigIntegerConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Long.class, new LongConverter());
		
		converters.addXmlAttributeToObjectConverter(
				Short.class, new ShortConverter());
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
