package org.adligo.xml_io.client.schema;

import java.util.Collection;

public interface I_Schema {

	public abstract Collection<I_Element> getElements();

	public abstract Collection<I_ComplexType> getComplexTypes();

	public abstract TargetNamespace getTargetNamespace();
	/**
	 * returns the schema xml file's string bytes
	 * @return
	 */
	public String getXmlString();
}