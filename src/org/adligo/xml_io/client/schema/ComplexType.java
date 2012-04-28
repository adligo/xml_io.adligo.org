package org.adligo.xml_io.client.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComplexType implements I_ComplexType {
	private ComplexTypeMutant other;
	private List<I_SequenceChild> sequence;
	
	public ComplexType(I_ComplexType p) {
		other = new ComplexTypeMutant(p);
		
		List<I_SequenceChild> immutableSequence = new ArrayList<I_SequenceChild>();
		List<I_SequenceChild> sequenceChildren = p.getSequence();
		for (I_SequenceChild child: sequenceChildren) {
			try {
				immutableSequence.add(new Any((I_Any) child));
			} catch (ClassCastException x) {
				//eat
			}
			try {
				immutableSequence.add(new SequenceElement((I_SequenceElement) child));
			} catch (ClassCastException x) {
				//eat
			}	
		}
		sequence = Collections.unmodifiableList(immutableSequence);
	}

	public List<I_SequenceChild> getSequence() {
		return sequence;
	}

	public String getName() {
		return other.getName();
	}
}
