package org.adligo.xml_io.shared.schema;

import java.util.Collection;
import java.util.List;

import org.adligo.models.params.shared.I_XMLBuilder;
import org.adligo.models.params.shared.XMLBuilder;
import org.adligo.xml_io.shared.TargetNamespace;
import org.adligo.xml_io.shared.Xml_IOConstants;

public class SchemaXmlBuilder {
	private I_XMLBuilder builder = new XMLBuilder();
	
	public String build(I_Schema schema) {
		if (schema == null) {
			throw new NullPointerException("SchemaXmlBuilder requires a schema");
		}
		builder.append(Xml_IOConstants.HEADER);
		builder.lineFeed();
		builder.appendTagHeaderStart(Xml_IOConstants.SCHEMA);
		builder.appendAttribute(Xml_IOConstants.XMLNS_ATTRIBUTE, Xml_IOConstants.XMLNS_WC3_2001);
		builder.lineFeed();
		TargetNamespace ns = schema.getTargetNamespace();
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
		
		Collection<I_Element> elements = schema.getElements();
		for (I_Element e : elements) {
			addElement(e);
		}
		builder.lineFeed();
		Collection<I_SimpleType> simpleTypes = schema.getSimpleTypes();
		for (I_SimpleType e : simpleTypes) {
			addSimpleType(e);
		}
		
		builder.lineFeed();
		Collection<I_ComplexType> complexTypes = schema.getComplexTypes();
		for (I_ComplexType e : complexTypes) {
			addComplexType(e);
		}
		builder.removeIndentLevel();
		builder.lineFeed();
		
		
		builder.appendEndTag(Xml_IOConstants.SCHEMA);
		return builder.toXmlString();
	}

	private void addElement(I_Element e) {
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.ELEMENT_TAG);
		String name = e.getName();
		builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
		String type = e.getType();
		if (type != null) {
			builder.appendAttribute(Xml_IOConstants.TYPE_ATTRIBUTE, type);
		}
		builder.appendTagHeaderEndNoChildren();
	}

	private void addComplexType(I_ComplexType e) {
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.COMPLEX_TYPE_TAG);
		String name = e.getName();
		builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
		builder.appendTagHeaderEnd(true);
		
		List<I_SequenceChild> children = e.getSequence();
		if (children.size() >= 1) {
			appendSequence(children);
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Xml_IOConstants.COMPLEX_TYPE_TAG);
	}

	private void addSimpleType(I_SimpleType e) {
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.SIMPLE_TYPE_TAG);
		String name = e.getName();
		builder.appendAttribute(Xml_IOConstants.NAME_ATTRIBUTE, name);
		builder.appendTagHeaderEnd(true);
		
		String baseType = e.getBaseType();
		builder.addIndentLevel();
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.RESTRICTION_TAG);
		builder.appendAttribute(Xml_IOConstants.BASE_ATTRIBUTE, baseType);
		builder.appendTagHeaderEnd(true);
		
		builder.addIndentLevel();
		String pattern = e.getPattern();
		if (pattern != null) {
			builder.indent();
			builder.appendTagHeaderStart(Xml_IOConstants.PATTERN_TAG);
			builder.appendAttribute(Xml_IOConstants.VALUE_ATTRIBUTE, baseType);
			builder.appendTagHeaderEndNoChildren();
		}
		
		String maxLength = e.getMaxLength();
		if (maxLength != null) {
			builder.indent();
			builder.appendTagHeaderStart(Xml_IOConstants.MAX_LENGTH_TAG);
			builder.appendAttribute(Xml_IOConstants.VALUE_ATTRIBUTE, maxLength);
			builder.appendTagHeaderEndNoChildren();
		}
		
		String minLength = e.getMinLength();
		if (minLength != null) {
			builder.indent();
			builder.appendTagHeaderStart(Xml_IOConstants.MIN_LENGTH_TAG);
			builder.appendAttribute(Xml_IOConstants.VALUE_ATTRIBUTE, minLength);
			builder.appendTagHeaderEndNoChildren();
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Xml_IOConstants.RESTRICTION_TAG);
		
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Xml_IOConstants.SIMPLE_TYPE_TAG);
	}

	private void appendSequence(List<I_SequenceChild> children) {
		
		builder.addIndentLevel();
		builder.indent();
		builder.appendTagHeaderStart(Xml_IOConstants.SEQUENCE_TAG);
		builder.appendTagHeaderEnd(true);
		builder.addIndentLevel();
		builder.indent();
		
		for (I_SequenceChild child: children) {
			SequenceChildTypes type = child.getNodeType();
			switch (type) {
				case ANY:
					appendSequenceAny(child);
					break;
				case ELEMENT:
					appendSequenceElements(child);
					break;
				default:
					throw new IllegalStateException("Unknown SequenceChildType " + type);
			}
		}
		builder.removeIndentLevel();
		builder.indent();
		builder.appendEndTag(Xml_IOConstants.SEQUENCE_TAG);
	}

	private void appendSequenceAny(I_SequenceChild child) {
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
			builder.appendTagHeaderEndNoChildren();
		} catch (ClassCastException x) {
			throw new IllegalStateException(x);
		}
	}
	
	private void appendSequenceElements(I_SequenceChild child) {
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
			builder.appendTagHeaderEndNoChildren();
		} catch (ClassCastException x) {
			throw new IllegalStateException(x);
		}
	}

	public I_XMLBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(I_XMLBuilder p) {
		if (p == null) {
			throw new NullPointerException("Builder may not be null");
		}
		this.builder = p;
	}

}
