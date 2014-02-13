package org.adligo.xml_io.shared.schema;

public class SequenceElementMutant implements I_SequenceElement {
	private String name;
	private String type;
	private String minOccurs;
	private String maxOccurs;
	
	public SequenceElementMutant() {}
	
	public SequenceElementMutant(I_SequenceElement other) {
		name = other.getName();
		type = other.getType();
		minOccurs = other.getMinOccurs();
		maxOccurs = other.getMaxOccurs();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SequenceElement#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SequenceElement#getType()
	 */
	@Override
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SequenceElement#getMinOccurs()
	 */
	@Override
	public String getMinOccurs() {
		return minOccurs;
	}
	public void setMinOccurs(String minOccurs) {
		this.minOccurs = minOccurs;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SequenceElement#getMaxOccurs()
	 */
	@Override
	public String getMaxOccurs() {
		return maxOccurs;
	}
	public void setMaxOccurs(String maxOccurs) {
		this.maxOccurs = maxOccurs;
	}
	
	@Override
	public SequenceChildTypes getNodeType() {
		return SequenceChildTypes.ELEMENT;
	}
}
