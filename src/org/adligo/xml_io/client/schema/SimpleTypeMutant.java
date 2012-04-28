package org.adligo.xml_io.client.schema;

public class SimpleTypeMutant implements I_SimpleType {
	/**
	 * the name of the simple type
	 * ie MyName in <simpleType name="MyName">
	 */
	private String name;
	/**
	 * the base type ie string in 
     * <simpleType name="MyType">
	 *	<restriction base="string">
	 */
	private String baseType;
	/**
	 * the regular expression to limit the type ie to t or f in the following example.
	 * <simpleType name="Boolean">
	 *	<restriction base="string">
	 *		<pattern value="[tf]"></pattern>
	 */
	private String pattern;
	/**
	 * the maximum number of characters, ie 1 in the following example.
	 * <simpleType name="Boolean">
	 *	<restriction base="string">
	 *		<maxLength value="1"></maxLength>
	 */
	private String maxLength;
	/**
	 * the maximum number of characters, ie 2 in the following example.
	 * <simpleType name="Boolean">
	 *	<restriction base="string">
	 *		<minLength value="2"></minLength>
	 */
	private String minLength;
	
	
	public SimpleTypeMutant() {}
	
	public SimpleTypeMutant(I_SimpleType p) {
		name = p.getName();
		baseType = p.getBaseType();
		pattern = p.getPattern();
		minLength = p.getMinLength();
		maxLength = p.getMaxLength();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SimpleType#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SimpleType#getBaseType()
	 */
	@Override
	public String getBaseType() {
		return baseType;
	}
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SimpleType#getPattern()
	 */
	@Override
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SimpleType#getMaxLength()
	 */
	@Override
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.schema.I_SimpleType#getMinLength()
	 */
	@Override
	public String getMinLength() {
		return minLength;
	}
	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}
}
