package org.adligo.xml_io.client.schema;

import java.util.Collection;
import java.util.List;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.XMLBuilder;
import org.adligo.xml_io.client.TargetNamespace;
import org.adligo.xml_io.client.Xml_IOConstants;

public class Schema implements I_Schema {
	private SchemaMutant other;
	
	public Schema(I_Schema p) {
		other = new SchemaMutant(p);
	}

	public Collection<I_Element> getElements() {
		return other.getElements();
	}

	public Collection<I_ComplexType> getComplexTypes() {
		return other.getComplexTypes();
	}

	public TargetNamespace getTargetNamespace() {
		return other.getTargetNamespace();
	}
	
	

	public Collection<I_SimpleType> getSimpleTypes() {
		return other.getSimpleTypes();
	}
}
