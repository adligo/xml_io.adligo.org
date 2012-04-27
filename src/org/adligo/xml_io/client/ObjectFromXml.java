package org.adligo.xml_io.client;

public class ObjectFromXml<T> {
	private String name;
	private T value;
	
	public ObjectFromXml(T val) {
		value = val;
	}

	public ObjectFromXml(String name, T val) {
		value = val;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
}
