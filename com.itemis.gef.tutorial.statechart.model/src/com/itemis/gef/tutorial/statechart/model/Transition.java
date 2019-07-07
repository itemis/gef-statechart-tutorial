package com.itemis.gef.tutorial.statechart.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.geometry.planar.Point;

public class Transition implements Serializable {

	// Semantic properties
	public static final String PROP_NAME = "name";

	// Notation properties
	public static final String PROP_BEND_POINTS = "bendPoints";

	// Semantic attributes
	private String name = "";
	private State source;
	private State target;
	private boolean connected;

	// Notation attributes
	List<Point> bendPoints = new ArrayList<>();

	private static final long serialVersionUID = 4246614394959971916L;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		pcs.firePropertyChange(PROP_NAME, oldName, name);
	}

	public State getSource() {
		return source;
	}

	public State getTarget() {
		return target;
	}

	public void connect(State source, State target) {
		if (source == null || target == null || source == target) {
			throw new IllegalArgumentException();
		}
		disconnect();
		this.source = source;
		this.target = target;
		reconnect();
	}

	public void disconnect() {
		if (connected) {
			source.removeOutgoingTransition(this);
			target.removeIncomingTransition(this);
			connected = false;
		}
	}

	public void reconnect() {
		if (!connected) {
			source.addOutgoingTransition(this);
			target.addIncomingTransition(this);
			connected = true;
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
