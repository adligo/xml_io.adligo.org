package org.adligo.xml_io.client.converters;

public class Tags {
	/**
	 * in order for this xml to interlop easier with other languages 
	 * (not just java) I am treating all collections the same,
	 * the language specific impl can then deal with the details
	 * (Similar to SOAP but I am using Web Sockets and or HTTP 
	 * 	as the transport usually, as well as using this format for 
	 *  disk storage.
	 * )
	 */
	public static final String LIST = "L";
	public static final String MAP = "m";
	public static final String KEY_VALUE = "k";
	
	public static final String LONG = "l";
	public static final String BIG_INTEGER = "I";
	public static final String INTEGER = "i";
	public static final String FLOAT = "f";
	public static final String BIG_DECIMAL = "D";
	public static final String DOUBLE = "d";
	public static final String CHARACTER = "C";
	public static final String SHORT = "S";
	public static final String STRING = "s";
	public static final String BOOLEAN = "b";
	public static final String BYTE = "B";
	
	/**
	 * treat these arrays special
	 */
	public static final String BYTE_ARRAY = "a";
	public static final String BOOlEAN_ARRAY = "A";
	public static final String CHAR_ARRAY = "c";
	
	public static final String NAME_ATTRIBUTE = "n";
}
