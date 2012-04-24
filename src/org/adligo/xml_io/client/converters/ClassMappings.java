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
		toRet.put(MAP_TAG, EnumMap.class);
		toRet.put(LIST_TAG, EnumSet.class);
		
		toRet.put(FLOAT_TAG, Float.class);
		
		toRet.put(MAP_TAG, HashMap.class);
		toRet.put(LIST_TAG, HashSet.class);
		
		toRet.put(INTEGER_TAG, Integer.class);
		toRet.put(BIG_INTEGER_TAG, BigInteger.class);
		
		toRet.put(MAP_TAG, IdentityHashMap.class);
		toRet.put(MAP_TAG, LinkedHashMap.class);
		
		toRet.put(LIST_TAG, LinkedHashSet.class);
		toRet.put(LIST_TAG, LinkedList.class);
		
		toRet.put(LONG_TAG, Long.class);
		toRet.put(LIST_TAG, ArrayList.class);
		
		toRet.put(LIST_TAG, Stack.class);
		
		toRet.put(LIST_TAG, PriorityQueue.class);
		
		toRet.put(STRING_TAG, String.class);
		toRet.put(SHORT_TAG, Short.class);
		
		toRet.put(MAP_TAG, TreeMap.class);
		toRet.put(LIST_TAG, TreeSet.class);
		
		toRet.put(LIST_TAG, Vector.class);
		
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
		
		toRet.put(LIST_TAG, new CollectionConverter());
		
		return Collections.unmodifiableMap(toRet);
	}
	private static final Map<Class<?>,I_Converter<?>> getObjectToXmlConverters() {
		Map<Class<?>,I_Converter<?>> toRet = new HashMap<Class<?>,I_Converter<?>>();
		
		CollectionConverter listConverter = new CollectionConverter();
		toRet.put(ArrayList.class, listConverter);
		
		toRet.put(Boolean.class, new BooleanConverter());
		toRet.put(Byte.class, new ByteConverter());
		
		toRet.put(Character.class, new CharacterConverter());
		toRet.put(Double.class, new DoubleConverter());
		toRet.put(BigDecimal.class, new BigDecimalConverter());
		
		//toRet.put(MAP_TAG, EnumMap.class);
		toRet.put(EnumSet.class, listConverter);
		
		toRet.put(Float.class, new FloatConverter());

		//toRet.put(HashMap.class);
		toRet.put(HashSet.class, listConverter);
		
		toRet.put(Integer.class, new IntegerConverter());
		toRet.put(BigInteger.class, new BigIntegerConverter());
		
		//toRet.put(MAP_TAG, IdentityHashMap.class);
		//toRet.put(MAP_TAG, LinkedHashMap.class);
		
		toRet.put(LinkedHashSet.class, listConverter);
		toRet.put(LinkedList.class, listConverter);
		
		toRet.put(Long.class, new LongConverter());
		toRet.put(ArrayList.class, listConverter);
		
		toRet.put(Stack.class, listConverter);
		toRet.put(PriorityQueue.class, listConverter);
		
		toRet.put(Short.class, new ShortConverter());
		toRet.put(String.class, new StringConverter());
		
		toRet.put(TreeSet.class, listConverter);
		toRet.put(Vector.class, listConverter);
		
		return Collections.unmodifiableMap(toRet);
	}
	
}
