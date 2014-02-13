package org.adligo.xml_io.shared.schema;

import java.util.Collection;

import org.adligo.xml_io.shared.TargetNamespace;

public interface I_Schema {

	public abstract Collection<I_Element> getElements();

	public abstract Collection<I_ComplexType> getComplexTypes();

	public abstract TargetNamespace getTargetNamespace();
	
	public Collection<I_SimpleType> getSimpleTypes();

}