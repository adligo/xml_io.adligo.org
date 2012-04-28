package org.adligo.xml_io.client.schema;

import java.util.Collection;
import java.util.List;

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

	public TargetNamespace getTargetNamespace() {
		return other.getTargetNamespace();
	}
	
	public static String toXmlString(I_Schema p) {
		I_XMLBuilder builder = new XMLBuilder();
		builder.append(Xml_IOConstants.HEADER);
		builder.lineFeed();
		builder.appendTagHeaderStart(Xml_IOConstants.SCHEMA);
		builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE, Xml_IOConstants.XMLNS_WC3_2001);
		builder.lineFeed();
		TargetNamespace ns = p.getTargetNamespace();
		String tns = ns.getPrefix();
		String targetNamespaceName = ns.getFullName();
		builder.appendAttribute(Xml_IOConstants.TARGET_NAMESPACE_ATTRIBUE, targetNamespaceName);
		builder.lineFeed();
		builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE + ":" + tns, targetNamespaceName);
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
		builder.lineFeed();
		Collection<I_ComplexType> complexTypes = p.getComplexTypes();
		for (I_ComplexType e : complexTypes) {
			builder.indent();
			builder.appendTagHeaderStart(Xml_IOConstants.COMPLEX_TYPE_TAG);
			String name = e.getName();
			builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
			builder.appendTagHeaderEnd(true);
			
			List<I_SequenceChild> children = e.getSequence();
			if (children.size() >= 1) {
				appendSequence(builder, children);
			}
			builder.removeIndentLevel();
			builder.indent();
			builder.appendEndTag(Xml_IOConstants.COMPLEX_TYPE_TAG);
		}
		builder.removeIndentLevel();
		builder.lineFeed();
		
		
		builder.appendEndTag(Xml_IOConstants.SCHEMA);
		return builder.toXmlString();
	}

	private static void appendSequence(I_XMLBuilder builder,
			List<I_SequenceChild> children) {
		
		builder.addIndentLevel();
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.SEQUENCE_TAG);
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		builder.indent();
		
		for (I_SequenceChild child: children) {
			if (!appendSequenceElements(builder, child)) {
				appendSequenceAny(builder, child);
			}
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Xml_IOConstants.SEQUENCE_TAG);
	}

	private static boolean appendSequenceAny(I_XMLBuilder builder, I_SequenceChild child) {
		try {
			I_Any any = (I_Any) child;
			builder.appendTagHeaderStart(Xml_IOConstants.ANY_TAG);
			String minOccure = any.getMinOccurs();
			if (minOccure != null) {
				builder.appendAttribute(Xml_IOConstants.MIN_OCCURS, minOccure);
			}
			String maxOccure = any.getMaxOccurs();
			if (maxOccure != null) {
				builder.appendAttribute(Xml_IOConstants.MAX_OCCURS, maxOccure);
			}
			builder.appendTagHeaderEnd(true);
			
			return true;
		} catch (ClassCastException x) {
			//eat
		}
		return false;
	}
	
	private static boolean appendSequenceElements(I_XMLBuilder builder, I_SequenceChild child) {
		try {
			I_SequenceElement element = (I_SequenceElement) child;
			builder.appendTagHeaderStart(Xml_IOConstants.ELEMENT_TAG);
			
			String name = element.getName();
			builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
			String type = element.getType();
			if (type != null) {
				builder.appendAttribute(Xml_IOConstants.TYPE_ATTRIBUTE, type);
			}
			String minOccure = element.getMinOccurs();
			if (minOccure != null) {
				builder.appendAttribute(Xml_IOConstants.MIN_OCCURS, minOccure);
			}
			String maxOccure = element.getMaxOccurs();
			if (maxOccure != null) {
				builder.appendAttribute(Xml_IOConstants.MAX_OCCURS, maxOccure);
			}
			builder.appendTagHeaderEnd(true);
			
			
			return true;
		} catch (ClassCastException x) {
			//eat
		}
		return false;
	}

	public String getXmlString() {
		return xmlString;
	}
}
