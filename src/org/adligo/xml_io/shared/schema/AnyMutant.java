package org.adligo.xml_io.shared.schema;

public class AnyMutant implements I_Any {
	private String minOccurs;
	private String maxOccurs;
	
	public AnyMutant() {
		
	}
	
	public AnyMutant(I_Any other) {
		minOccurs = other.getMinOccurs();
		maxOccurs = other.getMaxOccurs();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Any#getMinOccurs()
	 */
	@Override
	public String getMinOccurs() {
		return minOccurs;
	}
	public void setMinOccurs(String minOccurs) {
		this.minOccurs = minOccurs;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Any#getMaxOccurs()
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
		return SequenceChildTypes.ANY;
	}
}
