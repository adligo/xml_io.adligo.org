package org.adligo.xml_io.client.schema;

import java.util.Collections;
import java.util.List;

public class ComplexType implements I_ComplexType {
	private ComplexTypeMutant other;
	
	public ComplexType(I_ComplexType p) {
		other = new ComplexTypeMutant(p);
	}

	public List<I_SequenceChild> getSequence() {
		return Collections.unmodifiableList(other.getSequence());
	}

	public String getName() {
		return other.getName();
	}
}
