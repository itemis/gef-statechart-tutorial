package com.itemis.gef.tutorial.statechart.zest;

import java.util.Map;

import org.eclipse.gef.fx.nodes.Connection;
import org.eclipse.gef.mvc.fx.parts.IContentPart;
import org.eclipse.gef.zest.fx.ZestProperties;
import org.eclipse.gef.zest.fx.parts.EdgePart;
import org.eclipse.gef.zest.fx.parts.NodePart;
import org.eclipse.gef.zest.fx.parts.ZestFxContentPartFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.itemis.gef.tutorial.statechart.model.FinalState;
import com.itemis.gef.tutorial.statechart.model.InitialState;
import com.itemis.gef.tutorial.statechart.visuals.FinalStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.InitialStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.TransitionVisual;

import javafx.scene.Group;
import javafx.scene.Node;

public class StatechartContentPartFactory extends ZestFxContentPartFactory {

	@Inject
	private Injector injector;

	@Override
	public IContentPart<? extends Node> createContentPart(Object content, Map<Object, Object> contextMap) {
		if (content instanceof org.eclipse.gef.graph.Node) {
			IContentPart<Group> part = new NodePart() {
				@Override
				protected Group doCreateVisual() {
					// exchange visualization of the initial state
					Object label = getContent().getAttributes().get(ZestProperties.LABEL__NE);
					if (InitialState.class.getName().equals(label)) {
						return new InitialStateVisual();
					}
					// exchange visualization of the final state
					if (FinalState.class.getName().equals(label)) {
						return new FinalStateVisual();
					}
					return super.doCreateVisual();
				}

			};
			injector.injectMembers(part);
			return part;
		} else if (content instanceof org.eclipse.gef.graph.Edge) {
			IContentPart<Connection> part = new EdgePart() {
				@Override
				protected Connection doCreateVisual() {
					return new TransitionVisual();
				}
			};
			injector.injectMembers(part);
			return part;
		} else {
			return super.createContentPart(content, contextMap);
		}

	}

}
