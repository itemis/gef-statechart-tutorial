package com.itemis.gef.tutorial.statechart.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.geometry.planar.Rectangle;

public abstract class State implements Serializable {

	// Semantic properties
	public static final String PROP_INCOMING_TRANSITIONS = "incomingTransitions";
	public static final String PROP_OUTGOING_TRANSITIONS = "outgoingTransitions";

	// Notation properties
	public static final String PROP_BOUNDS = "bounds";

	// Semantic attributes
	private List<Transition> incomingTransitions = new ArrayList<>();
	private List<Transition> outgoingTransitions = new ArrayList<>();

	// Notation attributes
	private Rectangle bounds = new Rectangle();

	private static final long serialVersionUID = -2995806799558528686L;
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public List<Transition> getIncomingTransitions() {
		return new ArrayList<>(incomingTransitions);
	}

	public void addIncomingTransition(Transition transition) {
		incomingTransitions.add(transition);
		pcs.firePropertyChange(PROP_INCOMING_TRANSITIONS, null, transition);
	}

	public void removeIncomingTransition(Transition transition) {
		incomingTransitions.remove(transition);
		pcs.firePropertyChange(PROP_INCOMING_TRANSITIONS, transition, null);
	}

	public List<Transition> getOutgoingTransitions() {
		return new ArrayList<>(outgoingTransitions);
	}

	public List<Transition> getTransitions() {
		List<Transition> transitions = new ArrayList<Transition>();
		transitions.addAll(getIncomingTransitions());
		transitions.addAll(getOutgoingTransitions());
		return transitions;
	}

	public void addOutgoingTransition(Transition transition) {
		outgoingTransitions.add(transition);
		pcs.firePropertyChange(PROP_OUTGOING_TRANSITIONS, null, transition);
	}

	public void removeOutgoingTransition(Transition transition) {
		outgoingTransitions.remove(transition);
		pcs.firePropertyChange(PROP_OUTGOING_TRANSITIONS, transition, null);
	}

	public Rectangle getBounds() {
		return bounds.getCopy();
	}

	public void setBounds(Rectangle bounds) {
		Rectangle oldBounds = this.bounds;
		this.bounds = bounds.getCopy();
		pcs.firePropertyChange(PROP_BOUNDS, oldBounds, bounds);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
