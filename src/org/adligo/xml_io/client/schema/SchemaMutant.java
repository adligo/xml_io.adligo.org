package org.adligo.xml_io.client.schema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * note for my purposes a schema represents a single
 * schema only with no imports.
 * 
 * @author scott
 *
 */
public class SchemaMutant implements I_Schema {
	private TargetNamespace targetNamespace;
	private Map<String,I_Element> elements = new HashMap<String, I_Element>();
	private Map<String,I_ComplexType> complexTypes = new HashMap<String, I_ComplexType>();
	
	public SchemaMutant() {}
	
	public SchemaMutant(I_Schema p) {
		targetNamespace = p.getTargetNamespace();
		Collection<I_Element> elements = p.getElements();
		for (I_Element e: elements) {
			addElement(e);
		}
		Collection<I_ComplexType> complexTypes = p.getComplexTypes();
		for (I_ComplexType t: complexTypes) {
			addComplexType(t);
		}
	}
	
	public void addElement(I_Element p) {
		I_Element immute = new Element(p);
		String name = immute.getName();
		elements.put(name, immute);
	}
	
	public void addComplexType(I_ComplexType p) {
		I_ComplexType immute = new ComplexType(p);
		String name = immute.getName();
		complexTypes.put(name, immute);
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Schema#getElements()
	 */
	@Override
	public Collection<I_Element> getElements() {
		return elements.values();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Schema#getComplexTypes()
	 */
	@Override
	public Collection<I_ComplexType> getComplexTypes() {
		return complexTypes.values();
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Schema#getTargetNamespace()
	 */
	@Override
	public TargetNamespace getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(TargetNamespace targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	@Override
	public String getXmlString() {
		return Schema.toXmlString(this);
	}
}
