package org.adligo.xml_io.client;

import org.adligo.models.params.client.I_XMLBuilder;
import org.adligo.models.params.client.XMLBuilder;

public class XmlIOSettings {
	I_XMLBuilder builder = new XMLBuilder();

	public I_XMLBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(I_XMLBuilder builder) {
		this.builder = builder;
	}
	
}
