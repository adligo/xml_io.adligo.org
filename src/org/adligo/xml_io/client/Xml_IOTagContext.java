package org.adligo.xml_io.client;

public class Xml_IOTagContext {
	public static final String DEFAULT_NAMESPACE = "org.adligo.xml_io.client.default_namesapce";
	private String tagSuffix;
	private String namespace;
	
	public Xml_IOTagContext(String tagSuf, String ns) {
		if (tagSuf == null || ns == null) {
			throw new IllegalArgumentException("Xml_IOTagContext requires a tagSuffix and namespace");
		}
		tagSuffix = tagSuf;
		namespace = ns;
	}
	
	public String getTagSuffix() {
		return tagSuffix;
	}
	public String getNamespace() {
		return namespace;
	}
	public boolean isDefaultNamespace() {
		if (namespace.equals(DEFAULT_NAMESPACE)) {
			return true;
		}
		return false;
	}
	
}
