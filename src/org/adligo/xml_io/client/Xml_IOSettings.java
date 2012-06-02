package org.adligo.xml_io.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.converters.DefaultNamespaceConverters;

public class Xml_IOSettings {
	private I_XMLBuilder builder = new XMLBuilder();
	/**
	 * note if this is set by a external call,
	 * it overrides the settings read in from the xml
	 * so if they differ the parsing and writing of xml will
	 * probably not work correctly.
	 */
	private ConverterConfiguration config;
	

	/**
	 * map of namespaces to prefixes for writing of xml 
	 */
	private Map<String, String> _namespaceToPrefix = new HashMap<String, String>();
	private List<NamespaceConverters> namespaceConverters = new ArrayList<NamespaceConverters>();
	private LetterCounter prefixCounter = new LetterCounter();
	
	private boolean includeXmlHeader = true;
	private boolean includeXmlSchemaInfoInFirstTag = true;
	
	public Xml_IOSettings() {
		String prefix = prefixCounter.getNextId();
		_namespaceToPrefix.put(Xml_IOConstants.DEFAULT_NAMESPACE, prefix);
		namespaceConverters.add(DefaultNamespaceConverters.getDefaultNamespaceConverters());
	}
	
	public I_XMLBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(I_XMLBuilder builder) {
		this.builder = builder;
	}
	

	public boolean isIncludeXmlHeader() {
		return includeXmlHeader;
	}

	public void setIncludeXmlHeader(boolean includeXmlHeader) {
		this.includeXmlHeader = includeXmlHeader;
	}

	public boolean isIncludeXmlSchemaInfoInFirstTag() {
		return includeXmlSchemaInfoInFirstTag;
	}

	public void setIncludeXmlSchemaInfoInFirstTag(
			boolean includeXmlSchemaInfoInFirstTag) {
		this.includeXmlSchemaInfoInFirstTag = includeXmlSchemaInfoInFirstTag;
	}
	
	public Set<Entry<String, String>> getNamespaceEntries() {
		return _namespaceToPrefix.entrySet();
	}
	
	public void addNamespace(String namespace) {
		String prefix = prefixCounter.getNextId();
		_namespaceToPrefix.put(namespace, prefix);
	}

	void addNamespace(String namespace, String prefix) {
		_namespaceToPrefix.put(namespace, prefix);
	}
	
	public ConverterConfiguration getConfig() {
		return config;
	}

	public void setConfig(ConverterConfiguration config) {
		this.config = config;
		Map<String,String> namespaceToPrefix = config.getNamespaceToPrefix();
		Set<Entry<String,String>> entries = namespaceToPrefix.entrySet();
		for (Entry<String,String> ent: entries) {
			String namespace = ent.getKey();
			String prefix = ent.getValue();
			_namespaceToPrefix.put(namespace, prefix);
		}
	}
	
	public void setUpConfig() {
		if (config == null) {
			ConverterConfigurationMutant ccm = new ConverterConfigurationMutant();
			Set<Entry<String,String>> entries = _namespaceToPrefix.entrySet();
			for (Entry<String, String> e: entries) {
				String namespace = e.getKey();
				String prefix = e.getValue();
				ccm.addNamespacePrefix(prefix, namespace);
			}
			
			for (NamespaceConverters converters: namespaceConverters) {
				ccm.addNamespaceConverters(converters);
			}
			
			config = new ConverterConfiguration(ccm);
		}
	}

	public String getPrefix(String namespace) {
		return config.getPrefix(namespace);
	}

	public I_Converter<?> getFromXmlConverter(String tag) {
		return config.getFromXmlConverter(tag);
	}

	public I_Converter<?> getToXmlConverter(Class<?> clazz) {
		return config.getToXmlConverter(clazz);
	}

	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return config.getAttributeConverter(clazz);
	}
	
	public void addNamespaceConverter(NamespaceConverters converters) {
		String namespace = converters.getNamespace();
		addNamespace(namespace);
		namespaceConverters.add(converters);
	}
}
