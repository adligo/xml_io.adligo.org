package org.adligo.xml_io.client.schema;

public class Any implements I_Any {
	private AnyMutant other;
	
	public Any(I_Any p) {
		other = new AnyMutant(p);
	}

	public String getMinOccurs() {
		return other.getMinOccurs();
	}

	public String getMaxOccurs() {
		return other.getMaxOccurs();
	}
}
