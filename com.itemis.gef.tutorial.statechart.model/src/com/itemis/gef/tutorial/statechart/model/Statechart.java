package com.itemis.gef.tutorial.statechart.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.geometry.planar.Rectangle;

public class Statechart implements Serializable {

	public static Statechart createExample() {
		InitialState initialState = new InitialState();
		initialState.setBounds(new Rectangle(20, 20, 20, 20));

		SimpleState simpleState = new SimpleState();
		simpleState.setBounds(new Rectangle(100, 100, 60, 30));
		simpleState.setName("simple");

		Transition t1 = new Transition();
		t1.setName("t1");
		t1.connect(initialState, simpleState);

		FinalState finalState = new FinalState();
		finalState.setBounds(new Rectangle(200, 20, 25, 25));

		Transition t2 = new Transition();
		t2.setName("t2");
		t2.connect(simpleState, finalState);

		Statechart statechart = new Statechart();
		statechart.addState(initialState);
		statechart.addState(simpleState);
		statechart.addState(finalState);

		return statechart;
	}

	// Semantic properties
	public static final String PROP_STATES = "states";

	// Semantic attributes
	public List<State> states = new ArrayList<>();

	private static final long serialVersionUID = -3333092946245492751L;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public List<State> getStates() {
		return new ArrayList<>(states);
	}

	public List<Transition> getTransitions() {
		Set<Transition> transitions = new HashSet<>();
		for (State s : states) {
			transitions.addAll(s.getIncomingTransitions());
			transitions.addAll(s.getOutgoingTransitions());
		}
		return new ArrayList<>(transitions);
	}

	public void addState(State state) {
		states.add(state);
		pcs.firePropertyChange(PROP_STATES, null, state);
	}

	public void removeState(State state) {
		states.remove(state);
		pcs.firePropertyChange(PROP_STATES, state, null);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
