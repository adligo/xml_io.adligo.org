package org.adligo.xml_io.shared.schema;

import java.util.ArrayList;
import java.util.List;

public class ComplexTypeMutant implements I_ComplexType {
	private String name;
	private List<I_SequenceChild> sequence = new ArrayList<I_SequenceChild>();

	public ComplexTypeMutant() {}
	
	public ComplexTypeMutant(I_ComplexType other) {
		setSequence(other.getSequence());
		name = other.getName();
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_ComplexType#getSequence()
	 */
	@Override
	public List<I_SequenceChild> getSequence() {
		return sequence;
	}

	public void setSequence(List<I_SequenceChild> p) {
		sequence.clear();
		sequence.addAll(p);
	}

	public void addSequence(I_SequenceChild p) {
		sequence.add(p);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
