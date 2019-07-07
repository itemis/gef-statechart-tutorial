package com.itemis.gef.tutorial.statechart.model;

public class SimpleState extends State {

	// Semantic properties
	public static final String PROP_NAME = "name";

	// Semantic attributes
	private String name = "";

	private static final long serialVersionUID = 952029785010896181L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		pcs.firePropertyChange(PROP_NAME, oldName, name);
	}

}
