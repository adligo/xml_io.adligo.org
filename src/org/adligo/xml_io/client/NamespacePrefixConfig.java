package org.adligo.xml_io.client;

import java.util.Map;

public class NamespacePrefixConfig implements I_NamespacePrefixConfig {
	private NamespacePrefixConfigMutant mutant;
	
	public NamespacePrefixConfig() {
		mutant = new NamespacePrefixConfigMutant();
	}
	
	public NamespacePrefixConfig(I_NamespacePrefixConfig p) {
		mutant = new NamespacePrefixConfigMutant(p);
	}

	public Map<String, I_NamespaceConverters> getPrefixToConverters() {
		return mutant.getPrefixToConverters();
	}
}
