package org.adligo.xml_io.client.schema;

public class SimpleType implements I_SimpleType {
	private SimpleTypeMutant mutant;
	
	public SimpleType() {}
	
	public SimpleType(I_SimpleType other) {
		mutant = new SimpleTypeMutant(other);
	}

	public String getName() {
		return mutant.getName();
	}

	public String getBaseType() {
		return mutant.getBaseType();
	}

	public String getPattern() {
		return mutant.getPattern();
	}

	public String getMaxLength() {
		return mutant.getMaxLength();
	}

	public String getMinLength() {
		return mutant.getMinLength();
	}
}
