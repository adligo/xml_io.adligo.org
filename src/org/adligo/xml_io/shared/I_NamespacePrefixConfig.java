package org.adligo.xml_io.shared;

import java.util.Map;

public interface I_NamespacePrefixConfig {

	public abstract Map<String, NamespaceConverters> getPrefixToConverters();

}