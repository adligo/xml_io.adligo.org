package org.adligo.xml_io.shared.schema;

public class SequenceElement implements I_SequenceElement {
	private SequenceElementMutant other;
	
	public SequenceElement(I_SequenceElement p) {
		other = new SequenceElementMutant(p);
	}

	public String getName() {
		return other.getName();
	}

	public String getType() {
		return other.getType();
	}

	public String getMinOccurs() {
		return other.getMinOccurs();
	}

	public String getMaxOccurs() {
		return other.getMaxOccurs();
	}
	
	@Override
	public SequenceChildTypes getNodeType() {
		return SequenceChildTypes.ELEMENT;
	}
}
