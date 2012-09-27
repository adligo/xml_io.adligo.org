package org.adligo.xml_io.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.converters.DefaultNamespaceConverters;

/**
 * although this class is a mutant it is only inteded to be mutated by 
 * code writen by the developers, during reads and writes of xml 
 * it should be considered immutable.
 * 
 * @author scott
 *
 */
public class Xml_IOSettingsMutant implements I_Xml_IOSettings {
	public static final String NO_PREFIX_FOUND_FOR_NAMESPACE = "No prefix found for namespace ";

	/**
	 * @see I_Xml_IOSettings#isIncludeXmlHeader()
	 */
	private boolean includeXmlHeader = true;
	/**
	 * @see I_Xml_IOSettings#isIncludeXmlSchemaInfoInFirstTag()
	 */
	private boolean includeXmlSchemaInfoInFirstTag = true;
	/**
	 * @see I_Xml_IOSettings#isIgnoreFileNamespace()
	 */
	private boolean ignoreFileNamespace = false;
	
	private NamespacePrefixConfig config;
	/**
	 * Note all subsequent maps are
	 */
	private Map<String, NamespaceConverters> namespaceConverters = new HashMap<String, NamespaceConverters>();
	/**
	 * map of namespaces to prefixes for writing of xml 
	 */
	private Map<String, String> namespaceToPrefix = new HashMap<String, String>();
	/**
	 * map of namespaces to prefixes for reading of xml when 
	 * ignoreFileNamespace is set to true
	 */
	private Map<String, String> prefixToNamespace = new HashMap<String, String>();
	
	private Map<Class<?>, I_AttributeConverter<?>> attributeConverters = new HashMap<Class<?>, I_AttributeConverter<?>>();
	
	private Map<Class<?>, I_Converter<?>> objectConverters = new HashMap<Class<?>, I_Converter<?>>();
	
	public Xml_IOSettingsMutant() {
		
	}

	
	public Xml_IOSettingsMutant(I_Xml_IOSettings p) {
		includeXmlHeader = p.isIncludeXmlHeader();
		includeXmlSchemaInfoInFirstTag = p.isIncludeXmlSchemaInfoInFirstTag();
		ignoreFileNamespace = p.isIgnoreFileNamespace();
		
		if (p.getConfig() != null) {
			setConfig(p.getConfig());
		}
	}	

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#isIncludeXmlHeader()
	 */
	@Override
	public boolean isIncludeXmlHeader() {
		return includeXmlHeader;
	}

	public void setIncludeXmlHeader(boolean includeXmlHeader) {
		this.includeXmlHeader = includeXmlHeader;
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#isIncludeXmlSchemaInfoInFirstTag()
	 */
	@Override
	public boolean isIncludeXmlSchemaInfoInFirstTag() {
		return includeXmlSchemaInfoInFirstTag;
	}

	public void setIncludeXmlSchemaInfoInFirstTag(
			boolean includeXmlSchemaInfoInFirstTag) {
		this.includeXmlSchemaInfoInFirstTag = includeXmlSchemaInfoInFirstTag;
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#getNamespaceEntrie()
	 */
	@Override
	public Set<Entry<String, String>> getNamespaceEntries() {
		return namespaceToPrefix.entrySet();
	}
	
	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#getNamespaceEntriesMap()
	 */
	@Override
	public Map<String, String> getNamespaceEntriesMap() {
		return Collections.unmodifiableMap(namespaceToPrefix);
	}
	
	
	public String getPrefix(String namespace) {
		String toRet = namespaceToPrefix.get(namespace);
		if (toRet == null) {
			throw new NullPointerException(NO_PREFIX_FOUND_FOR_NAMESPACE + namespace);
		}
		return toRet;
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#getFromXmlConverter(org.adligo.xml_io.client.Xml_IOTagContext)
	 */
	@Override
	public I_Converter<?> getFromXmlConverter(Xml_IOTagContext tag) {
		String ns = tag.getNamespace();
		NamespaceConverters converters = namespaceConverters.get(ns);
		if (converters != null) {
			String tagName = tag.getTagSuffix();
			I_Converter<?> toRet = converters.getXmlToObjectConverter(tagName);
			return toRet;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#getToXmlConverter(java.lang.Class)
	 */
	@Override
	public I_Converter<?> getToXmlConverter(Class<?> clazz) {
		return objectConverters.get(clazz);
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#getAttributeConverter(java.lang.Class)
	 */
	@Override
	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return attributeConverters.get(clazz);
	}
	
	public void setConfig(NamespacePrefixConfig p) {
		config = p;
		Map<String, I_NamespaceConverters> configMap = config.getPrefixToConverters();
		Set<Entry<String, I_NamespaceConverters>> entries = configMap.entrySet();
		for (Entry<String, I_NamespaceConverters> e: entries) {
			String prefix = e.getKey();
			I_NamespaceConverters nc = e.getValue();
			
			NamespaceConverters nc2 = new NamespaceConverters(nc);
			String namespace = nc.getNamespace();
			namespaceToPrefix.put(namespace, prefix);
			prefixToNamespace.put(prefix, namespace);
			namespaceConverters.put(namespace, nc2);
			
			attributeConverters.putAll(nc2.getAttributeConverters());
			objectConverters.putAll(nc2.getObjectToXmlConverters());
		}
	}

	/* (non-Javadoc)
	 * @see org.adligo.xml_io.client.I_Xml_IOSettings#isIgnoreFileNamespace()
	 */
	@Override
	public boolean isIgnoreFileNamespace() {
		return ignoreFileNamespace;
	}

	public void setIgnoreFileNamespace(boolean ignoreFileNamespace) {
		this.ignoreFileNamespace = ignoreFileNamespace;
	}

	public Map<String, NamespaceConverters> getNamespaceConverters() {
		return Collections.unmodifiableMap(namespaceConverters);
	}


	public NamespacePrefixConfig getConfig() {
		return config;
	}


	@Override
	public String getNamespaceForDefaultPrefix(String prefix) {
		return prefixToNamespace.get(prefix);
	}
}
