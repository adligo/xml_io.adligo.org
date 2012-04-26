package org.adligo.xml_io.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.converters.ClassMappings;

public class Xml_IOWriterContext {
	public static final String COULD_NOT_FIND_A_CONVERTER_FOR_CLASS = 
		"Could not find a converter for class ";
	
	private Xml_IOWriter writer;
	private I_XMLBuilder builder = new XMLBuilder();
	private Xml_IOSettings settings;
	
	/**
	 * this is usually null, but may be set to a value to have the name 
	 * added to the xml
	 * ie 
	 * <L n="myList"><i>1</i><i>2</i></L>
	 */
	private String nextTagNameAttribute = null;
	
	Xml_IOWriterContext() {}
	
	public Xml_IOWriter getWriter() {
		return writer;
	}
	void setWriter(Xml_IOWriter writer) {
		this.writer = writer;
	}
	public Xml_IOSettings getSettings() {
		return settings;
	}
	void setSettings(Xml_IOSettings settings) {
		this.settings = settings;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeXml(Object p) {
		if (p == null) {
			return;
		}
		Class<?> clazz = p.getClass();
		Object toConvert = p;
		
		if (isArrayWithOutConverter(clazz)) {
			Object [] objs =  toObjectArray(p, clazz);
			ArrayList list = new ArrayList();
			clazz = list.getClass();
			Collections.addAll(list, objs);
			toConvert = list;
		} 
		I_Converter<Object> converter = (I_Converter<Object>) 
					settings.getToXmlConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_CONVERTER_FOR_CLASS 
					+ clazz);
		}
		converter.toXml(toConvert, this);
	}

	
	public I_XMLBuilder getBuilder() {
		return builder;
	}
	
	private boolean isArrayWithOutConverter(Class<?> clazz) {
		if (!clazz.isArray()) {
			return false;
		}
		if (ClassMappings.BYTE_ARRAY_CLASS.equals(clazz)) {
			return false;
		}
		if (ClassMappings.BOOLEAN_ARRAY_CLASS.equals(clazz)) {
			return false;
		}
		if (ClassMappings.CHAR_ARRAY_CLASS.equals(clazz)) {
			return false;
		}
		return true;
	}
	
	public Object [] toObjectArray(Object p, Class<?> clazz) {
		try {
			return (Object []) p;
		} catch (ClassCastException x) {
			//eat
		}
		Object [] toRet = null;
		try {
			short [] vals = (short []) p;
			toRet = new Object[vals.length];
			for (int i = 0; i < vals.length; i++) {
				toRet[i] = vals[i];
			}
		} catch (ClassCastException x) {
			//eat
		}
		try {
			int [] vals = (int []) p;
			toRet = new Object[vals.length];
			for (int i = 0; i < vals.length; i++) {
				toRet[i] = vals[i];
			}
		} catch (ClassCastException x) {
			//eat
		}
		try {
			long [] vals = (long []) p;
			toRet = new Object[vals.length];
			for (int i = 0; i < vals.length; i++) {
				toRet[i] = vals[i];
			}
		} catch (ClassCastException x) {
			//eat
		}
		try {
			double [] vals = (double []) p;
			toRet = new Object[vals.length];
			for (int i = 0; i < vals.length; i++) {
				toRet[i] = vals[i];
			}
		} catch (ClassCastException x) {
			//eat
		}
		try {
			float [] vals = (float []) p;
			toRet = new Object[vals.length];
			for (int i = 0; i < vals.length; i++) {
				toRet[i] = vals[i];
			}
		} catch (ClassCastException x) {
			//eat
		}
		return toRet;
	}

	public String getNextTagNameAttribute() {
		return nextTagNameAttribute;
	}

	public void setNextTagNameAttribute(String nextTagNameAttribute) {
		this.nextTagNameAttribute = nextTagNameAttribute;
	}
}
