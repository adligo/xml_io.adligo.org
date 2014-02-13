package org.adligo.xml_io.shared.schema;

public interface I_SequenceElement extends I_SequenceChild {

	public abstract String getName();

	public abstract String getType();

	public abstract String getMinOccurs();

	public abstract String getMaxOccurs();

}