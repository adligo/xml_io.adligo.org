package org.adligo.xml_io.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * this class should not be considered thread safe one per thread
 * 
 * @author scott
 *
 */
public class Xml_IOSettings implements I_Xml_IOSettings {
	private Xml_IOSettingsMutant mutant;
	
	public int hashCode() {
		return mutant.hashCode();
	}

	public boolean isIncludeXmlHeader() {
		return mutant.isIncludeXmlHeader();
	}

	public void setIncludeXmlHeader(boolean includeXmlHeader) {
		mutant.setIncludeXmlHeader(includeXmlHeader);
	}

	public boolean isIncludeXmlSchemaInfoInFirstTag() {
		return mutant.isIncludeXmlSchemaInfoInFirstTag();
	}

	public void setIncludeXmlSchemaInfoInFirstTag(
			boolean includeXmlSchemaInfoInFirstTag) {
		mutant.setIncludeXmlSchemaInfoInFirstTag(includeXmlSchemaInfoInFirstTag);
	}

	public Set<Entry<String, String>> getNamespaceEntries() {
		return mutant.getNamespaceEntries();
	}

	public Map<String, String> getNamespaceEntriesMap() {
		return mutant.getNamespaceEntriesMap();
	}

	public String getPrefix(String namespace) {
		return mutant.getPrefix(namespace);
	}

	public I_Converter<?> getFromXmlConverter(Xml_IOTagContext tag) {
		return mutant.getFromXmlConverter(tag);
	}

	public boolean equals(Object obj) {
		return mutant.equals(obj);
	}

	public I_Converter<?> getToXmlConverter(Class<?> clazz) {
		return mutant.getToXmlConverter(clazz);
	}

	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return mutant.getAttributeConverter(clazz);
	}

	public void setConfig(NamespacePrefixConfig p) {
		mutant.setConfig(p);
	}

	public boolean isIgnoreFileNamespace() {
		return mutant.isIgnoreFileNamespace();
	}

	public void setIgnoreFileNamespace(boolean ignoreFileNamespace) {
		mutant.setIgnoreFileNamespace(ignoreFileNamespace);
	}

	public Map<String, NamespaceConverters> getNamespaceConverters() {
		return mutant.getNamespaceConverters();
	}

	public NamespacePrefixConfig getConfig() {
		return mutant.getConfig();
	}

	public String getNamespaceForDefaultPrefix(String prefix) {
		return mutant.getNamespaceForDefaultPrefix(prefix);
	}

	public String getIndent() {
		return mutant.getIndent();
	}

	public void setIndent(String indent) {
		mutant.setIndent(indent);
	}

	public String getLinefeed() {
		return mutant.getLinefeed();
	}

	public void setLinefeed(String linefeed) {
		mutant.setLinefeed(linefeed);
	}

	public Xml_IOSettings(I_Xml_IOSettings p) {
		mutant = new Xml_IOSettingsMutant(p);
	}
}
