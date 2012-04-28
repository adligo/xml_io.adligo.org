package org.adligo.xml_io.client.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.adligo.xml_io.client.I_AttributeConverter;
import org.adligo.xml_io.client.I_Converter;
import org.adligo.xml_io.client.Xml_IOConstants;

/**
 * provide single mappings of characters to most of the serlizable types in GWT
 * with the exception of java.sql stuff and Date (use Long and or the adligo DateTime class).
 * 
 * @author scott
 *
 */
public class ClassMappings {

	
	public static final Class<?> BYTE_ARRAY_CLASS = (new byte[] {}).getClass();
	public static final Class<?> BOOLEAN_ARRAY_CLASS = (new boolean[] {}).getClass();
	public static final Class<?> CHAR_ARRAY_CLASS = (new char[] {}).getClass();
	
	
	@SuppressWarnings("rawtypes")
	public static final Set<Class> COLLECTION_CLASSES = getCollectionClasses();
	@SuppressWarnings("rawtypes")
	public static final Set<Class> MAP_CLASSES = getMapClasses();
	
	public static final Map<String,I_Converter<?>> DEFAULT_XML_TO_OBJECT_CONVERTERS = getXmlToObjectConverters();
	public static final Map<Class<?>,I_Converter<?>> DEFAULT_OBJECT_TO_XML_CONVERTERS = getObjectToXmlConverters();
	
	public static final Map<Class<?>,I_AttributeConverter<?>> DEFAULT_XML_ATTRIBUTE_TO_OBJECT_CONVERTERS = getClassToAttributeConvertersMap();
	public static final Map<Class<?>,I_AttributeConverter<?>> DEFAULT_OBJECT_TO_XML_ATTRIBUTE_CONVERTERS = getClassToAttributeConvertersMap();
	
	
	@SuppressWarnings("rawtypes")
	private static Set<Class> getCollectionClasses() {
		Set<Class> toRet = new HashSet<Class>();
		toRet.add(EnumSet.class);
		toRet.add(HashSet.class);
		toRet.add(LinkedHashSet.class);
		toRet.add(LinkedList.class);
		toRet.add(ArrayList.class);
		toRet.add(SortedSet.class);
		
		toRet.add(PriorityQueue.class);
		toRet.add(TreeSet.class);
		//vector will need to be treated as it"s own case
		return Collections.unmodifiableSet(toRet);
	}
	
	@SuppressWarnings("rawtypes")
	private static Set<Class> getMapClasses() {
		Set<Class> toRet = new HashSet<Class>();
		toRet.add(EnumMap.class);
		toRet.add(HashMap.class);
		toRet.add(IdentityHashMap.class);
		toRet.add(LinkedHashMap.class);
		toRet.add(SortedMap.class);
		toRet.add(TreeMap.class);
		
		//vector will need to be treated as it"s own case
		return Collections.unmodifiableSet(toRet);
	}
	
	private static Map<String,I_Converter<?>> getXmlToObjectConverters() {
		Map<String,I_Converter<?>> toRet = new HashMap<String, I_Converter<?>>();

		MapConverter mapConverter = new MapConverter();
		toRet.put(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, new BooleanConverter());
		toRet.put(Xml_IOConstants.BYTE_TAG_SUFFIX, new ByteConverter());
		
		toRet.put(Xml_IOConstants.CHARACTER_TAG_SUFFIX, new CharacterConverter());
		toRet.put(Xml_IOConstants.DOUBLE_TAG_SUFFIX, new DoubleConverter());
		toRet.put(Xml_IOConstants.BIG_DECIMAL_TAG_SUFFIX, new BigDecimalConverter());
		
		toRet.put(Xml_IOConstants.FLOAT_TAG_SUFFIX, new FloatConverter());
		
		toRet.put(Xml_IOConstants.INTEGER_TAG_SUFFIX, new IntegerConverter());
		toRet.put(Xml_IOConstants.BIG_INTEGER_TAG_SUFFIX, new BigIntegerConverter());
		
		toRet.put(Xml_IOConstants.LONG_TAG_SUFFIX, new LongConverter());
		
		toRet.put(Xml_IOConstants.SHORT_TAG_SUFFIX, new ShortConverter());
		toRet.put(Xml_IOConstants.STRING_TAG_SUFFIX, new StringConverter());
		
		toRet.put(Xml_IOConstants.LIST_TAG_SUFFIX, new CollectionConverter());
		toRet.put(Xml_IOConstants.MAP_TAG_SUFFIX, mapConverter);
		
		toRet.put(Xml_IOConstants.BYTE_ARRAY_TAG_SUFFIX, new ByteArrayConverter());
		toRet.put(Xml_IOConstants.BOOlEAN_ARRAY_TAG_SUFFIX, new BooleanArrayConverter());
		toRet.put(Xml_IOConstants.CHAR_ARRAY_TAG_SUFFIX, new CharArrayConverter());
		
		return Collections.unmodifiableMap(toRet);
	}

	private static final Map<Class<?>,I_Converter<?>> getObjectToXmlConverters() {
		Map<Class<?>,I_Converter<?>> toRet = new HashMap<Class<?>,I_Converter<?>>();
		
		MapConverter mapConverter = new MapConverter();
		CollectionConverter collectionConverter = new CollectionConverter();
		toRet.put(ArrayList.class, collectionConverter);
		
		toRet.put(Boolean.class, new BooleanConverter());
		toRet.put(Byte.class, new ByteConverter());
		
		toRet.put(Character.class, new CharacterConverter());
		toRet.put(Double.class, new DoubleConverter());
		toRet.put(BigDecimal.class, new BigDecimalConverter());
		
		//toRet.put(MAP, EnumMap.class);
		toRet.put(EnumSet.class, collectionConverter);
		
		toRet.put(Float.class, new FloatConverter());

		toRet.put(HashMap.class, mapConverter);
		toRet.put(HashSet.class, collectionConverter);
		
		toRet.put(Integer.class, new IntegerConverter());
		toRet.put(BigInteger.class, new BigIntegerConverter());
		
		toRet.put(IdentityHashMap.class, mapConverter);
		toRet.put(LinkedHashMap.class, mapConverter);
		
		toRet.put(LinkedHashSet.class, collectionConverter);
		toRet.put(LinkedList.class, collectionConverter);
		
		toRet.put(Long.class, new LongConverter());
		toRet.put(ArrayList.class, collectionConverter);
		
		toRet.put(Stack.class, collectionConverter);
		toRet.put(PriorityQueue.class, collectionConverter);
		
		toRet.put(Short.class, new ShortConverter());
		toRet.put(String.class, new StringConverter());
		
		toRet.put(TreeSet.class, collectionConverter);
		toRet.put(TreeMap.class, mapConverter);
		toRet.put(Vector.class, collectionConverter);
		
		toRet.put(BYTE_ARRAY_CLASS, new ByteArrayConverter());
		toRet.put(BOOLEAN_ARRAY_CLASS, new BooleanArrayConverter());
		toRet.put(CHAR_ARRAY_CLASS, new CharArrayConverter());
		
		return Collections.unmodifiableMap(toRet);
	}
	
	private static final Map<Class<?>,I_AttributeConverter<?>> getClassToAttributeConvertersMap() {
		Map<Class<?>,I_AttributeConverter<?>> toRet = new HashMap<Class<?>,I_AttributeConverter<?>>();
		
		toRet.put(Boolean.class, new BooleanConverter());
		toRet.put(Byte.class, new ByteConverter());
		
		toRet.put(Character.class, new CharacterConverter());
		toRet.put(Double.class, new DoubleConverter());
		toRet.put(BigDecimal.class, new BigDecimalConverter());
		
		toRet.put(Float.class, new FloatConverter());

		toRet.put(Integer.class, new IntegerConverter());
		toRet.put(BigInteger.class, new BigIntegerConverter());
		
		toRet.put(Long.class, new LongConverter());
		
		toRet.put(Short.class, new ShortConverter());
		toRet.put(String.class, new StringConverter());
		
		
		toRet.put(BYTE_ARRAY_CLASS, new ByteArrayConverter());
		toRet.put(BOOLEAN_ARRAY_CLASS, new BooleanArrayConverter());
		toRet.put(CHAR_ARRAY_CLASS, new CharArrayConverter());
		
		return Collections.unmodifiableMap(toRet);
	}
}
