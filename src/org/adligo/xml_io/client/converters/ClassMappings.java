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

	

	public static final Map<Class<?>,I_Converter<?>> DEFAULT_OBJECT_TO_XML_CONVERTERS = getObjectToXmlConverters();
	
	

	private static final Map<Class<?>,I_Converter<?>> getObjectToXmlConverters() {
		Map<Class<?>,I_Converter<?>> toRet = new HashMap<Class<?>,I_Converter<?>>();
		
		
		
		return Collections.unmodifiableMap(toRet);
	}
	
}
