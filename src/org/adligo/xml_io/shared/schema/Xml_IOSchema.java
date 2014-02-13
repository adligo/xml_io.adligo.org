package org.adligo.xml_io.shared.schema;

import org.adligo.xml_io.shared.TargetNamespace;
import org.adligo.xml_io.shared.Xml_IOConstants;

public class Xml_IOSchema {
	public static final String BOOLEAN = "Boolean";
	public static final String BOOLEAN_ARRAY = "BooleanArray";
	public static final String CHARACTER = "Character";
	
	public static final String MAP = "Map";
	public static final String KEY_VALUE = "KeyValue";
	public static final String COLLECTION = "Collection";
	public static final I_Schema SCHEMA = getSchema();
	
	private static I_Schema getSchema() {
		SchemaMutant toRet = new SchemaMutant();
		toRet.setTargetNamespace(new TargetNamespace("a",Xml_IOConstants.DEFAULT_NAMESPACE));
		
		addSimpleElement(Xml_IOConstants.LONG_TAG_SUFFIX, XMLTypes.LONG, toRet);
		addSimpleElement(Xml_IOConstants.BIG_INTEGER_TAG_SUFFIX, XMLTypes.INTEGER, toRet);
		addSimpleElement(Xml_IOConstants.INTEGER_TAG_SUFFIX, XMLTypes.INT, toRet);
		addSimpleElement(Xml_IOConstants.FLOAT_TAG_SUFFIX, XMLTypes.FLOAT, toRet);
		
		addSimpleElement(Xml_IOConstants.BIG_DECIMAL_TAG_SUFFIX, XMLTypes.DECIMAL, toRet);
		addSimpleElement(Xml_IOConstants.DOUBLE_TAG_SUFFIX, XMLTypes.DOUBLE, toRet);
		addSimpleElement(Xml_IOConstants.CHARACTER_TAG_SUFFIX, "a:" + CHARACTER, toRet);
		addSimpleElement(Xml_IOConstants.SHORT_TAG_SUFFIX, XMLTypes.SHORT, toRet);
		addSimpleElement(Xml_IOConstants.STRING_TAG_SUFFIX, XMLTypes.STRING, toRet);
		
		addSimpleElement(Xml_IOConstants.BOOLEAN_TAG_SUFFIX, "a:" + BOOLEAN, toRet);
		addSimpleElement(Xml_IOConstants.BYTE_TAG_SUFFIX, XMLTypes.BASE_64, toRet);
		
		addSimpleElement(Xml_IOConstants.BOOlEAN_ARRAY_TAG_SUFFIX, "a:" + BOOLEAN_ARRAY, toRet);
		addSimpleElement(Xml_IOConstants.BYTE_ARRAY_TAG_SUFFIX, XMLTypes.BASE_64, toRet);
		addSimpleElement(Xml_IOConstants.CHAR_ARRAY_TAG_SUFFIX, XMLTypes.STRING, toRet);
		
		addSimpleTypes(toRet);
		
		addComplexElements(toRet);
		
		return new Schema(toRet);
	}

	private static void addSimpleElement(String name, String type, SchemaMutant toRet) {
		ElementMutant em = new ElementMutant();
		em.setName(name);
		em.setType(type);
		toRet.addElement(em);
	}
	
	private static void addComplexElements(SchemaMutant toRet) {
		ComplexTypeMutant collection = new ComplexTypeMutant();
		collection.setName(COLLECTION);
		AnyMutant any = new AnyMutant();
		any.setMinOccurs("1");
		any.setMaxOccurs(Xml_IOConstants.UNBOUNDED);
		collection.addSequence(any);
		
		toRet.addComplexType(collection);
		addSimpleElement(Xml_IOConstants.LIST_TAG_SUFFIX, "a:" + COLLECTION, toRet);
		
		
		ComplexTypeMutant entry = new ComplexTypeMutant();
		entry.setName(KEY_VALUE);
		AnyMutant keyValueAny = new AnyMutant();
		keyValueAny.setMinOccurs("1");
		keyValueAny.setMaxOccurs("2");
		entry.addSequence(keyValueAny);
		
		toRet.addComplexType(entry);
		addSimpleElement(Xml_IOConstants.KEY_VALUE_TAG_SUFFIX, "a:" + KEY_VALUE, toRet);
		
		
		ComplexTypeMutant map = new ComplexTypeMutant();
		map.setName(MAP);
		SequenceElementMutant element = new SequenceElementMutant();
		element.setName(Xml_IOConstants.KEY_VALUE_TAG_SUFFIX);
		element.setType("a:" + KEY_VALUE);
		element.setMinOccurs("1");
		element.setMaxOccurs(Xml_IOConstants.UNBOUNDED);
		map.addSequence(element);
		
		toRet.addComplexType(map);
		addSimpleElement(Xml_IOConstants.MAP_TAG_SUFFIX, "a:" + MAP, toRet);
	}

	private static void addSimpleTypes(SchemaMutant toRet) {
		SimpleTypeMutant character = new SimpleTypeMutant();
		character.setName(CHARACTER);
		character.setMaxLength("1");
		character.setBaseType(XMLTypes.STRING);
		toRet.addSimpleType(character);
		
		SimpleTypeMutant bool = new SimpleTypeMutant();
		bool.setName(BOOLEAN);
		bool.setBaseType(XMLTypes.STRING);
		bool.setPattern("[tf]");
		bool.setMaxLength("1");
		toRet.addSimpleType(bool);
		
		SimpleTypeMutant boolArray = new SimpleTypeMutant();
		boolArray.setName(BOOLEAN_ARRAY);
		boolArray.setBaseType(XMLTypes.STRING);
		boolArray.setPattern("[tf]");
		toRet.addSimpleType(boolArray);
	}
}
