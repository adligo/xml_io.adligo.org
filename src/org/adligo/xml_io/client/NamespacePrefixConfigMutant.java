package org.adligo.xml_io.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.adligo.xml_io.client.converters.DefaultNamespaceConverters;

/**
 * this class allows the user to configure the namespaces to their prefixes
 * it will auto create prefixes
 * or the developer may specify.
 * 
 * It is recommended to either specify all of the prefixes or non as specifying some of the
 * prefixes will result in issues.
 * Also note that the prefix a is reserved for the namespace http://www.adligo.org/xml_io.
 * 
 * @author scott
 *
 */
public class NamespacePrefixConfigMutant implements I_NamespacePrefixConfig {
	private Map<String, I_NamespaceConverters> prefixToConverters = new HashMap<String, I_NamespaceConverters>();
	private LetterCounter letterCounter = new LetterCounter();
	
	public NamespacePrefixConfigMutant() {
		prefixToConverters.put("a",DefaultNamespaceConverters.getDefaultNamespaceConverters());
		letterCounter.getNextId();
	}
	
	public NamespacePrefixConfigMutant(I_NamespacePrefixConfig config) {
		prefixToConverters.putAll(config.getPrefixToConverters());
	}
	
	public void addNamespace(I_NamespaceConverters nc) {
		String prefix = letterCounter.getNextId();
		prefixToConverters.put(prefix, nc);
	}
	
	public void addNamespace(String prefix, I_NamespaceConverters nc) {
		prefixToConverters.put(prefix, nc);
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_NamespacePrefixConfig#getPrefixToConverters()
	 */
	@Override
	public Map<String, I_NamespaceConverters> getPrefixToConverters() {
		return Collections.unmodifiableMap(prefixToConverters);
	}
}
