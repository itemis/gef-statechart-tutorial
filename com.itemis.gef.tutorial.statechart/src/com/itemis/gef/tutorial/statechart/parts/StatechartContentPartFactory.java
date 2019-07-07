package com.itemis.gef.tutorial.statechart.parts;

import java.util.Map;

import org.eclipse.gef.mvc.fx.parts.IContentPart;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.itemis.gef.tutorial.statechart.model.FinalState;
import com.itemis.gef.tutorial.statechart.model.InitialState;
import com.itemis.gef.tutorial.statechart.model.SimpleState;
import com.itemis.gef.tutorial.statechart.model.Statechart;
import com.itemis.gef.tutorial.statechart.model.Transition;

import javafx.scene.Node;

public class StatechartContentPartFactory implements IContentPartFactory {

	@Inject
	private Injector injector;

	@Override
	public IContentPart<? extends Node> createContentPart(Object content, Map<Object, Object> contextMap) {
		if (content == null) {
			throw new IllegalArgumentException("Content must not be null!");
		}

		if (content instanceof Statechart) {
			return injector.getInstance(StatechartPart.class);
		} else if (content instanceof InitialState) {
			return injector.getInstance(InitialStatePart.class);
		} else if (content instanceof SimpleState) {
			return injector.getInstance(SimpleStatePart.class);
		} else if (content instanceof FinalState) {
			return injector.getInstance(FinalStatePart.class);
		} else if (content instanceof Transition) {
			return injector.getInstance(TransitionPart.class);
		} else {
			throw new IllegalArgumentException("Unknown content type <" + content.getClass().getName() + ">");
		}
	}

}
