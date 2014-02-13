package org.adligo.xml_io.shared.schema;

public class Element implements I_Element {
	private ElementMutant other;

	public Element(I_Element p) {
		other = new ElementMutant(p);
	}
	
	public String getName() {
		return other.getName();
	}

	public String getType() {
		return other.getType();
	}
	
}
