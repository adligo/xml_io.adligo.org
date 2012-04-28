package org.adligo.xml_io.client.schema;

import java.util.Collection;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;
import org.adligo.xml_io.client.Xml_IOConstants;

public class Schema implements I_Schema {
	private SchemaMutant other;
	private String xmlString;
	
	public Schema(I_Schema p) {
		other = new SchemaMutant(p);
		xmlString = toXmlString(this);
	}

	public Collection<I_Element> getElements() {
		return other.getElements();
	}

	public Collection<I_ComplexType> getComplexTypes() {
		return other.getComplexTypes();
	}

	public String getTargetNamespace() {
		return other.getTargetNamespace();
	}
	
	public static String toXmlString(I_Schema p) {
		I_XMLBuilder builder = new XMLBuilder();
		builder.append(Xml_IOConstants.HEADER);
		builder.lineFeed();
		builder.appendTagHeaderStart(Xml_IOConstants.SCHEMA);
		builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE, Xml_IOConstants.XMLNS_WC3_2001);
		builder.lineFeed();
		String targetNamespace = p.getTargetNamespace();
		builder.appendAttribute(Xml_IOConstants.TARGET_NAMESPACE_ATTRIBUE, targetNamespace);
		builder.lineFeed();
		builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE + ":a", targetNamespace);
		builder.lineFeed();
		builder.appendAttribute(Xml_IOConstants.SCHEMA_ELEMENT_FORM_DEFAULT_ATTRIBUTE, 
				Xml_IOConstants.SCHEMA_QUALIFIED);
		builder.appendTagHeaderEnd(true);
		builder.lineFeed();
		builder.addIndentLevel();
		
		Collection<I_Element> elements = p.getElements();
		for (I_Element e : elements) {
			builder.indent();
			builder.appendTagHeaderStart(Xml_IOConstants.ELEMENT_TAG);
			String name = e.getName();
			builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
			String type = e.getType();
			if (type != null) {
				builder.appendAttribute(Xml_IOConstants.TYPE_ATTRIBUTE, type);
			}
			builder.appendTagHeaderEnd(true);
		}
		builder.removeIndentLevel();
		builder.lineFeed();
		
		
		builder.appendEndTag(Xml_IOConstants.SCHEMA);
		return builder.toXmlString();
	}

	public String getXmlString() {
		return xmlString;
	}
}
