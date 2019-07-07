package com.itemis.gef.tutorial.statechart.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.itemis.gef.tutorial.statechart.model.State;
import com.itemis.gef.tutorial.statechart.model.Statechart;
import com.itemis.gef.tutorial.statechart.model.Transition;

import javafx.scene.Group;
import javafx.scene.Node;

public class StatechartPart extends AbstractContentPart<Group> {

	@Override
	public Statechart getContent() {
		return (Statechart) super.getContent();
	}

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		return HashMultimap.create();
	}

	@Override
	protected List<? extends Object> doGetContentChildren() {
		List<Object> children = new ArrayList<Object>();

		Statechart statechart = getContent();

		// walk over the incoming and the outgoing transitions to find all children
		// parts
		Set<Transition> visited = Collections.newSetFromMap(new IdentityHashMap<Transition, Boolean>());

		for (State state : statechart.getStates()) {
			children.add(state);
			// add incoming and outgoing transitions
			for (Transition transition : state.getTransitions()) {
				if (!visited.contains(transition)) {
					children.add(transition);
					visited.add(transition);
				}
			}
		}

		return children;
	}

	@Override
	protected Group doCreateVisual() {
		return new Group();
	}

	@Override
	protected void doRefreshVisual(Group visual) {
		// nothing to do
	}

	@Override
	protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().add(index, child.getVisual());
	}

	@Override
	protected void doRemoveChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().remove(child.getVisual());
	}

}
