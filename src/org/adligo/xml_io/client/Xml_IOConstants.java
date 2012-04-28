package org.adligo.xml_io.client;

import java.io.UnsupportedEncodingException;

public class Xml_IOConstants {
	public static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	/**
	 * in order for this xml to interlop easier with other languages 
	 * (not just java) I am treating all collections the same,
	 * the language specific impl can then deal with the details
	 * (Similar to SOAP but I am using Web Sockets and or HTTP 
	 * 	as the transport usually, as well as using this format for 
	 *  disk storage.
	 * )
	 */
	public static final String LIST_TAG_SUFFIX = "L";
	public static final String MAP_TAG_SUFFIX = "m";
	public static final String KEY_VALUE_TAG_SUFFIX = "k";
	
	public static final String LONG_TAG_SUFFIX = "l";
	public static final String BIG_INTEGER_TAG_SUFFIX = "I";
	public static final String INTEGER_TAG_SUFFIX = "i";
	public static final String FLOAT_TAG_SUFFIX = "f";
	public static final String BIG_DECIMAL_TAG_SUFFIX = "D";
	public static final String DOUBLE_TAG_SUFFIX = "d";
	public static final String CHARACTER_TAG_SUFFIX = "C";
	public static final String SHORT_TAG_SUFFIX = "S";
	public static final String STRING_TAG_SUFFIX = "s";
	public static final String BOOLEAN_TAG_SUFFIX = "b";
	public static final String BYTE_TAG_SUFFIX = "B";
	
	/**
	 * treat these arrays special
	 */
	public static final String BYTE_ARRAY_TAG_SUFFIX = "a";
	public static final String BOOlEAN_ARRAY_TAG_SUFFIX = "A";
	public static final String CHAR_ARRAY_TAG_SUFFIX = "c";
	
	public static final String N_NAME_ATTRIBUTE = "n";
	
	public static final String SCHEMA = "schema";
	public static final String XMLNS_ATTRIBUTE = "xmlns";
	public static final String XMLNS_WC3_2001 = 
				"http://www.w3.org/2001/XMLSchema";
	public static final String XMLNS_WC3_2001_INSTANCE = 
		"http://www.w3.org/2001/XMLSchema-instance";
	public static final String TARGET_NAMESPACE_ATTRIBUE = 
				"targetNamespace";
	public static final String SCHEMA_ELEMENT_FORM_DEFAULT_ATTRIBUTE = 
				"elementFormDefault";
	public static final String SCHEMA_QUALIFIED = 
				"qualified";
	public static final String ELEMENT_TAG = "element";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String TYPE_ATTRIBUTE = "type";
	public static final String COMPLEX_TYPE_TAG = "complexType";
	public static final String SEQUENCE_TAG = "sequence";
	public static final String ANY_TAG = "any";
	public static final String UNBOUNDED = "unbounded";
	public static final String MIN_OCCURS = "minOccurs"; 
	public static final String MAX_OCCURS = "maxOccurs";
	
	public static final String SIMPLE_TYPE_TAG = "simpleType";
	public static final String RESTRICTION_TAG = "restriction";
	public static final String BASE_ATTRIBUTE = "base";
	public static final String PATTERN_TAG = "pattern";
	public static final String VALUE_ATTRIBUTE = "value";
	public static final String MAX_LENGTH_TAG = "maxLength";
	public static final String MIN_LENGTH_TAG = "minLength";
	
	public static final String DEFAULT_NAMESPACE = "http://www.adligo.org/xml_io";
	
}
