package org.adligo.xml_io.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.XMLBuilder;
import org.adligo.xml_io.shared.converters.DefaultNamespaceConverters;

/**
 * this class should not be considered thread safe one per thread
 * @author scott
 *
 */
public class Xml_IOWriterContext {
	public static final String COULD_NOT_FIND_A_CONVERTER_FOR_CLASS = 
		"Could not find a converter for class ";
	public static final String COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS = 
		"Could not find a attribute converter for class ";
	
	private Xml_IOWriter writer;
	private I_XMLBuilder builder = new XMLBuilder();
	private I_Xml_IOSettings settings;
	
	/**
	 * this is usually null, but may be set to a value to have the name 
	 * added to the xml
	 * ie 
	 * <L n="myList"><i>1</i><i>2</i></L>
	 */
	private String nextTagNameAttribute = null;
	private boolean firstTag = true;
	private String defaultNamespacePrefix = null;
	
	Xml_IOWriterContext() {}
	
	public Xml_IOWriter getWriter() {
		return writer;
	}
	void setWriter(Xml_IOWriter writer) {
		this.writer = writer;
	}
	public I_Xml_IOSettings getSettings() {
		return settings;
	}
	void setSettings(I_Xml_IOSettings settings) {
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
			clazz = Collection.class;
			Collections.addAll(list, objs);
			toConvert = list;
		} else if (Collection.class.isAssignableFrom(clazz)) {
			clazz = Collection.class;
		} else if (Map.class.isAssignableFrom(clazz)) {
			clazz = Map.class;
		}
		I_Converter<Object> converter = (I_Converter<Object>) 
					settings.getToXmlConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(COULD_NOT_FIND_A_CONVERTER_FOR_CLASS 
					+ clazz);
		}
		converter.toXml(toConvert, this);
		nextTagNameAttribute = null;
	}

	@SuppressWarnings({ "unchecked"})
	public void writeXmlAttribute(String name, Object p) {
		if (p == null) {
			return;
		}
		Class<?> clazz = p.getClass();
		
		I_AttributeConverter<Object> converter = (I_AttributeConverter<Object>) 
					settings.getAttributeConverter(clazz);
		if (converter == null) {
			throw new IllegalArgumentException(
					COULD_NOT_FIND_A_ATTRIBUTE_CONVERTER_FOR_CLASS 
					+ clazz);
		}
		nextTagNameAttribute = name;
		converter.toXmlAttribute(p, this);
		nextTagNameAttribute = null;
	}
	
	public I_XMLBuilder getBuilder() {
		return builder;
	}
	
	private boolean isArrayWithOutConverter(Class<?> clazz) {
		if (!clazz.isArray()) {
			return false;
		}
		if (DefaultNamespaceConverters.BYTE_ARRAY_CLASS.equals(clazz)) {
			return false;
		}
		if (DefaultNamespaceConverters.BOOLEAN_ARRAY_CLASS.equals(clazz)) {
			return false;
		}
		if (DefaultNamespaceConverters.CHAR_ARRAY_CLASS.equals(clazz)) {
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
	
	/**
	 * note this can be omitted to keep the xml smaller (less bytes)
	 */
	public void appendSchemaInfoToFirstTag() {
		if (firstTag) {
			firstTag = false;
			if (settings.isIncludeXmlSchemaInfoInFirstTag()) {
				Set<Entry<String,String>> namespaces = settings.getNamespaceEntries();
				for (Entry<String,String> space: namespaces) {
					String namespace = space.getKey();
					String prefix = space.getValue();
					builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE + ":" + prefix, namespace);
					builder.lineFeed();
				}
				builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE + ":xsi", Xml_IOConstants.XMLNS_WC3_2001_INSTANCE);
			}
		}
	}
	
	public String getNamespacePrefix(String namespace) {
		return settings.getPrefix(namespace);
	}
	
	public void appendDefaultTag(String tagName, String value) {
		getDefaultNamespace();
		builder.indent();
		builder.appendTagHeaderStart(defaultNamespacePrefix + tagName);
		appendSchemaInfoToFirstTag();
		builder.appendTagHeaderEnd(false);
		builder.append(value);
		builder.appendEndTag(defaultNamespacePrefix + tagName);
	}
	
	public void appendDefaultTagBase64(String tagName, byte [] value) {
		getDefaultNamespace();
		builder.indent();
		builder.appendTagHeaderStart(defaultNamespacePrefix + tagName);
		appendSchemaInfoToFirstTag();
		builder.appendTagHeaderEnd(false);
		builder.appendBase64(value);
		builder.appendEndTag(defaultNamespacePrefix + tagName);
	}
	
	public void appendTagHeaderStart(String tagName) {
		builder.indent();
		getDefaultNamespace();
		builder.appendTagHeaderStart(defaultNamespacePrefix + tagName);
	}

	public void appendTagHeaderStart(String namespace, String tagName) {
		builder.indent();
		String prefix = settings.getPrefix(namespace);
		builder.appendTagHeaderStart(prefix + ":" + tagName);
	}
	
	public void appendEndTag(String tagName) {
		builder.indent();
		getDefaultNamespace();
		builder.appendEndTag(defaultNamespacePrefix + tagName);
	}

	private void getDefaultNamespace() {
		if (defaultNamespacePrefix == null) {
			defaultNamespacePrefix = getNamespacePrefix(Xml_IOConstants.DEFAULT_NAMESPACE) + ":";
		}
	}

	public void appendEndTag(String namespace, String tagName) {
		builder.indent();
		String prefix = settings.getPrefix(namespace);
		builder.appendEndTag(prefix + ":" + tagName);
	}
}
