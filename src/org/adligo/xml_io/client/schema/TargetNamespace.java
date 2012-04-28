package org.adligo.xml_io.client.schema;

public class TargetNamespace {
	private String prefix;
	private String fullName;
	
	public TargetNamespace(String prefix, String fullName) {
		this.prefix = prefix;
		this.fullName = fullName;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public String getFullName() {
		return fullName;
	}
	
}
