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
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.adligo.xml_io.client.I_Converter;

/**
 * provide single mappings of characters to most of the serlizable types in GWT
 * with the exception of java.sql stuff and Date (use Long and or the adligo DateTime class).
 * 
 * @author scott
 *
 */
public class ClassMappings {
	/**
	 * in order for this xml to interlop easier with other languages 
	 * (not just java) I am treating all collections the same,
	 * the language specific impl can then deal with the details
	 * (Similar to SOAP but I am using Web Sockets and or HTTP 
	 * 	as the transport usually, as well as using this format for 
	 *  disk storage.
	 * )
	 */
	public static final String LIST_TAG = "L";
	public static final String MAP_TAG = "m";
	public static final String KEY_VALUE_TAG = "k";
	
	public static final String LONG_TAG = "l";
	public static final String BIG_INTEGER_TAG = "I";
	public static final String INTEGER_TAG = "i";
	public static final String FLOAT_TAG = "f";
	public static final String BIG_DECIMAL_TAG = "D";
	public static final String DOUBLE_TAG = "d";
	public static final String CHARACTER_TAG = "C";
	public static final String SHORT_TAG = "S";
	public static final String STRING_TAG = "s";
	public static final String BOOLEAN_TAG = "b";
	public static final String BYTE_TAG = "B";
	
	/**
	 * treat these arrays special
	 */
	public static final String BYTE_ARRAY_TAG = "a";
	public static final String BOOlEAN_ARRAY_TAG = "A";
	public static final String CHAR_ARRAY_TAG = "c";
	
	public static final Class<?> BYTE_ARRAY_CLASS = (new byte[] {}).getClass();
	public static final Class<?> BOOLEAN_ARRAY_CLASS = (new boolean[] {}).getClass();
	public static final Class<?> CHAR_ARRAY_CLASS = (new char[] {}).getClass();
	
	
	@SuppressWarnings("rawtypes")
	public static final Set<Class> COLLECTION_CLASSES = getCollectionClasses();
	@SuppressWarnings("rawtypes")
	public static final Set<Class> MAP_CLASSES = getMapClasses();
	
	public static final Map<String,I_Converter<?>> DEFAULT_XML_TO_OBJECT_CONVERTERS = getXmlToObjectConverters();
	public static final Map<Class<?>,I_Converter<?>> DEFAULT_OBJECT_TO_XML_CONVERTERS = getObjectToXmlConverters();
	

	
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
		toRet.put(BOOLEAN_TAG, new BooleanConverter());
		toRet.put(BYTE_TAG, new ByteConverter());
		
		toRet.put(CHARACTER_TAG, new CharacterConverter());
		toRet.put(DOUBLE_TAG, new DoubleConverter());
		toRet.put(BIG_DECIMAL_TAG, new BigDecimalConverter());
		
		toRet.put(FLOAT_TAG, new FloatConverter());
		
		toRet.put(INTEGER_TAG, new IntegerConverter());
		toRet.put(BIG_INTEGER_TAG, new BigIntegerConverter());
		
		toRet.put(LONG_TAG, new LongConverter());
		
		toRet.put(SHORT_TAG, new ShortConverter());
		toRet.put(STRING_TAG, new StringConverter());
		
		toRet.put(LIST_TAG, new CollectionConverter());
		toRet.put(MAP_TAG, mapConverter);
		
		toRet.put(BYTE_ARRAY_TAG, new ByteArrayConverter());
		toRet.put(BOOlEAN_ARRAY_TAG, new BooleanArrayConverter());
		toRet.put(CHAR_ARRAY_TAG, new CharArrayConverter());
		
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
		
		//toRet.put(MAP_TAG, EnumMap.class);
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
	
}
