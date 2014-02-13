package org.adligo.xml_io.shared;

import java.util.Map;
import java.util.Set;

/**
 * this class should not be considered thread safe
 * use one per thread.
 * 
 * @author scott
 *
 */
public class NamespaceConverters implements I_NamespaceConverters {
	private NamespaceConvertersMutant mutant;
	
	public NamespaceConverters() {
		mutant = new NamespaceConvertersMutant();
	}
	
	public NamespaceConverters(I_NamespaceConverters p) {
		mutant = new NamespaceConvertersMutant(p);
	}

	public String getNamespace() {
		return mutant.getNamespace();
	}

	public Map<String, I_Converter<?>> getXmlToObjectConverters() {
		return mutant.getXmlToObjectConverters();
	}

	public I_Converter<?> getXmlToObjectConverter(String tagName) {
		return mutant.getXmlToObjectConverter(tagName);
	}

	public Map<Class<?>, I_AttributeConverter<?>> getAttributeConverters() {
		return mutant.getAttributeConverters();
	}

	public I_AttributeConverter<?> getAttributeConverter(Class<?> clazz) {
		return mutant.getAttributeConverter(clazz);
	}

	public Map<Class<?>, I_Converter<?>> getObjectToXmlConverters() {
		return mutant.getObjectToXmlConverters();
	}

	public I_Converter<?> getObjectToXmlConverter(Class<?> c) {
		return mutant.getObjectToXmlConverter(c);
	}

	public String getPackageName() {
		return mutant.getPackageName();
	}
	
	public Set<Class<?>> getSupportedClasses() {
		return mutant.getSupportedClasses();
	}

	
}
