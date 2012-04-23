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

/**
 * provide single mappings of characters to most of the serlizable types in GWT
 * with the exception of java.sql stuff and Date (use Long and or the adligo DateTime class).
 * 
 * @author scott
 *
 */
public class ClassMappings {
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
	/**
	 * note the byte tag is used for single bytes and
	 * byte arrays (since the parser/builder can't distinguish)
	 * 
	 */
	public static final String BYTE_TAG = "B";
	@SuppressWarnings("rawtypes")
	public static final Map<String, Class> CHAR_TO_CLASS = getCharToClass();
	@SuppressWarnings("rawtypes")
	public static final Map<Class, String> CLASS_TO_CHAR = getClassToChar();
	@SuppressWarnings("rawtypes")
	public static final Set<Class> COLLECTION_CLASSES = getCollectionClasses();
	@SuppressWarnings("rawtypes")
	public static final Set<Class> MAP_CLASSES = getMapClasses();
	
	public static final Map<String,I_Converter<?>> DEFAULT_XML_TO_OBJECT_CONVERTERS = getXmlToObjectConverters();
	public static final Map<Class<?>,I_Converter<?>> DEFAULT_OBJECT_TO_XML_CONVERTERS = getObjectToXmlConverters();
	
	@SuppressWarnings("rawtypes")
	private static Map<String, Class> getCharToClass() {
		Map<String, Class> toRet = new HashMap<String, Class>();
		toRet.put(BOOLEAN_TAG, Boolean.class);
		toRet.put(BYTE_TAG, Byte.class);
		
		toRet.put(CHARACTER_TAG, Character.class);
		
		toRet.put(DOUBLE_TAG, Double.class);
		toRet.put(BIG_DECIMAL_TAG, BigDecimal.class);
		
		//note enums can't be auto generated this way,
		// so a concrete converter class will need to be 
		// created for each enum type
		toRet.put("E", EnumMap.class);
		toRet.put("F", EnumSet.class);
		
		toRet.put(FLOAT_TAG, Float.class);
		
		toRet.put("h", HashMap.class);
		toRet.put("H", HashSet.class);
		
		toRet.put(INTEGER_TAG, Integer.class);
		toRet.put(BIG_INTEGER_TAG, BigInteger.class);
		
		toRet.put("j", IdentityHashMap.class);
		toRet.put("J", LinkedHashMap.class);
		
		toRet.put("k", LinkedHashSet.class);
		toRet.put("K", LinkedList.class);
		
		toRet.put(LONG_TAG, Long.class);
		toRet.put("L", ArrayList.class);
		
		toRet.put("m", SortedSet.class);
		toRet.put("M", SortedMap.class);
		
		toRet.put("n", Stack.class);
		
		toRet.put("p", PriorityQueue.class);
		
		toRet.put(STRING_TAG, String.class);
		toRet.put(SHORT_TAG, Short.class);
		
		toRet.put("t", TreeMap.class);
		toRet.put("T", TreeSet.class);
		
		toRet.put("v", Vector.class);
		
		return Collections.unmodifiableMap(toRet);
	}
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, String> getClassToChar() {
		
		Map<Class, String> toRet = new HashMap<Class, String>();
		
		Set<Entry<String, Class>> entries = CHAR_TO_CLASS.entrySet();
		for (Entry<String, Class> e: entries) {
			String c = e.getKey();
			Class clazz = e.getValue();
			toRet.put(clazz, c);
		}
		
		return Collections.unmodifiableMap(toRet);
	}
	
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
		
		return Collections.unmodifiableMap(toRet);
	}
	private static final Map<Class<?>,I_Converter<?>> getObjectToXmlConverters() {
		Map<Class<?>,I_Converter<?>> toRet = new HashMap<Class<?>,I_Converter<?>>();
		
		
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
		
		return Collections.unmodifiableMap(toRet);
	}
	
}
