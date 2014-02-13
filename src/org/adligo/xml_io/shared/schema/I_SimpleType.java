package org.adligo.xml_io.shared.schema;

public interface I_SimpleType {

	public abstract String getName();

	public abstract String getBaseType();

	public abstract String getPattern();

	public abstract String getMaxLength();

	public abstract String getMinLength();

}