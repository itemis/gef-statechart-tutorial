package com.itemis.gef.tutorial.statechart.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.fx.anchors.IAnchor;
import org.eclipse.gef.fx.nodes.Connection;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import com.google.inject.Provider;
import com.itemis.gef.tutorial.statechart.model.Transition;
import com.itemis.gef.tutorial.statechart.visuals.TransitionVisual;

import javafx.scene.Node;

public class TransitionPart extends AbstractContentPart<Connection> {

	private static final String END_ROLE = "END";
	private static final String START_ROLE = "START";

	@SuppressWarnings("serial")
	@Override
	protected void doAttachToAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
		IAnchor anchor = anchorage.getAdapter(new TypeToken<Provider<? extends IAnchor>>() {
		}).get();
		Connection connection = getVisual();
		switch (role) {
		case START_ROLE:
			connection.setStartAnchor(anchor);
			break;
		case END_ROLE:
			connection.setEndAnchor(anchor);
			break;
		default:
			throw new IllegalStateException("Cannot attach to anchor with role <" + role + ">.");
		}
	}

	@Override
	protected void doDetachFromAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
		Connection connection = getVisual();
		switch (role) {
		case START_ROLE:
			connection.setStartPoint(connection.getStartPoint());
			break;
		case END_ROLE:
			connection.setEndPoint(connection.getEndPoint());
			break;
		default:
			throw new IllegalStateException("Cannot attach to anchor with role <" + role + ">.");
		}
	}

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		SetMultimap<Object, String> anchorages = HashMultimap.create();

		Transition transition = getContent();
		anchorages.put(transition.getSource(), START_ROLE);
		anchorages.put(transition.getTarget(), END_ROLE);

		return anchorages;
	}

	@Override
	protected List<? extends Object> doGetContentChildren() {
		return Collections.emptyList();
	}

	@Override
	protected Connection doCreateVisual() {
		return new TransitionVisual();
	}

	@Override
	protected void doRefreshVisual(Connection visual) {
		// nothing to do

	}

	@Override
	public Transition getContent() {
		return (Transition) super.getContent();
	}

}
