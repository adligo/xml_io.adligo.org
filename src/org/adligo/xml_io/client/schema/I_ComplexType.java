package org.adligo.xml_io.client.schema;

import java.util.List;

public interface I_ComplexType {
	public String getName();
	public abstract List<I_SequenceChild> getSequence();

}