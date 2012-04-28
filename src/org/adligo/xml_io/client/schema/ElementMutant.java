package org.adligo.xml_io.client.schema;

public class ElementMutant implements I_Element {
	private String name;
	private String type;
	
	public ElementMutant() {}
	
	public ElementMutant(I_Element other) {
		name = other.getName();
		type = other.getType();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Element#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_Element#getType()
	 */
	@Override
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
