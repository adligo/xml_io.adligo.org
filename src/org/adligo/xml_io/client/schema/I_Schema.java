package org.adligo.xml_io.client.schema;

import java.util.Collection;

import org.adligo.xml_io.client.TargetNamespace;

public interface I_Schema {

	public abstract Collection<I_Element> getElements();

	public abstract Collection<I_ComplexType> getComplexTypes();

	public abstract TargetNamespace getTargetNamespace();
	
	public Collection<I_SimpleType> getSimpleTypes();

}