package org.adligo.xml_io.client;

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
	
	@SuppressWarnings("rawtypes")
	private static Map<String, Class> getCharToClass() {
		Map<String, Class> toRet = new HashMap<String, Class>();
		toRet.put("b", Boolean.class);
		toRet.put(BYTE_TAG, Byte.class);
		
		toRet.put("c", Class.class);
		toRet.put("C", Character.class);
		
		toRet.put("d", Double.class);
		toRet.put("D", BigDecimal.class);
		
		toRet.put("e", Enum.class);
		toRet.put("E", EnumMap.class);
		toRet.put("F", EnumSet.class);
		
		toRet.put("f", Float.class);
		
		toRet.put("h", HashMap.class);
		toRet.put("H", HashSet.class);
		
		toRet.put("i", Integer.class);
		toRet.put("I", BigInteger.class);
		
		toRet.put("j", IdentityHashMap.class);
		toRet.put("J", LinkedHashMap.class);
		
		toRet.put("k", LinkedHashSet.class);
		toRet.put("K", LinkedList.class);
		
		toRet.put("l", Long.class);
		toRet.put("L", ArrayList.class);
		
		toRet.put("m", SortedSet.class);
		toRet.put("M", SortedMap.class);
		
		toRet.put("n", Stack.class);
		
		toRet.put("p", PriorityQueue.class);
		
		toRet.put("s", String.class);
		toRet.put("S", Short.class);
		
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
}
