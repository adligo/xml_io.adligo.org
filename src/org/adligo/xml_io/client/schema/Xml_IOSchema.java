package org.adligo.xml_io.client.schema;

import org.adligo.xml_io.client.Xml_IOConstants;

public class Xml_IOSchema {
	public static final I_Schema SCHEMA = getSchema();
	
	private static I_Schema getSchema() {
		SchemaMutant toRet = new SchemaMutant();
		toRet.setTargetNamespace("http://www.adligo.org/xml_io");
		
		addSimpleElement(Xml_IOConstants.LONG_TAG_SUFFIX, XMLTypes.LONG, toRet);
		addSimpleElement(Xml_IOConstants.BIG_INTEGER_TAG_SUFFIX, XMLTypes.INTEGER, toRet);
		addSimpleElement(Xml_IOConstants.INTEGER_TAG_SUFFIX, XMLTypes.INT, toRet);
		addSimpleElement(Xml_IOConstants.FLOAT_TAG_SUFFIX, XMLTypes.FLOAT, toRet);
		
		addSimpleElement(Xml_IOConstants.BIG_DECIMAL_TAG_SUFFIX, XMLTypes.DECIMAL, toRet);
		addSimpleElement(Xml_IOConstants.DOUBLE_TAG_SUFFIX, XMLTypes.DOUBLE, toRet);
		addSimpleElement(Xml_IOConstants.CHARACTER_TAG_SUFFIX, XMLTypes.STRING, toRet);
		addSimpleElement(Xml_IOConstants.SHORT_TAG_SUFFIX, XMLTypes.SHORT, toRet);
		addSimpleElement(Xml_IOConstants.STRING_TAG_SUFFIX, XMLTypes.STRING, toRet);
		
		addSimpleElement(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, XMLTypes.STRING, toRet);
		addSimpleElement(Xml_IOConstants.BYTE_TAG_SUFFIX, XMLTypes.BASE_64, toRet);
		
		addSimpleElement(Xml_IOConstants.BOOlEAN_ARRAY_TAG_SUFFIX, XMLTypes.STRING, toRet);
		addSimpleElement(Xml_IOConstants.BYTE_ARRAY_TAG_SUFFIX, XMLTypes.BASE_64, toRet);
		addSimpleElement(Xml_IOConstants.CHAR_ARRAY_TAG_SUFFIX, XMLTypes.STRING, toRet);
		
		return new Schema(toRet);
	}

	private static void addSimpleElement(String name, String type, SchemaMutant toRet) {
		ElementMutant em = new ElementMutant();
		em.setName(name);
		em.setType(type);
		toRet.addElement(em);
	}
	
}
