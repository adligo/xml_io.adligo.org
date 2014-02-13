package org.adligo.xml_io.shared;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * represents the settings to use to convert to and from xml.
 * 
 * @author scott
 *
 */
public interface I_Xml_IOSettings {

	/**
	 * If this is true the xml header ie;
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * should be written out
	 * @return
	 */
	public abstract boolean isIncludeXmlHeader();
	/**
	 * if this is true the first tag should contain schema info ie;
	 * <g:Person xmlns:g="http://www.adligo.org/models"
	 * exc
	 * 
	 * @return
	 */
	public abstract boolean isIncludeXmlSchemaInfoInFirstTag();

	public abstract Set<Entry<String, String>> getNamespaceEntries();

	public Map<String, String> getNamespaceEntriesMap();
	
	public abstract I_Converter<?> getFromXmlConverter(Xml_IOTagContext tag);

	public abstract I_Converter<?> getToXmlConverter(Class<?> clazz);

	public abstract I_AttributeConverter<?> getAttributeConverter(Class<?> clazz);

	/**
	 * if this is true the in memory prefix settings should be used (ie same as writing)
	 * if this is set to false (the default)
	 *    the parser should read the namespace prefixes from the first xml tag ie 
	 *    <g:Person xmlns:g="http://www.adligo.org/models"
	 *    
	 * @return
	 */
	public abstract boolean isIgnoreFileNamespace();
	
	public String getPrefix(String namespace);

	public NamespacePrefixConfig getConfig();
	
	public String getNamespaceForDefaultPrefix(String prefix);
	
	/**
	 * if null use defaults otherwise what you set it to  
	 * @return
	 */
	public abstract String getIndent();
	
	/**
	 * if null use defaults otherwise what you set it to
	 * @return
	 */
	public abstract String getLinefeed();
}