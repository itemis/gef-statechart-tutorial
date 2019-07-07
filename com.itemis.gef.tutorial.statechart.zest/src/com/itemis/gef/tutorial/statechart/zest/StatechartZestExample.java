package com.itemis.gef.tutorial.statechart.zest;

import org.eclipse.gef.common.adapt.inject.AdaptableScopes;
import org.eclipse.gef.graph.Graph;
import org.eclipse.gef.layout.algorithms.RadialLayoutAlgorithm;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;
import org.eclipse.gef.mvc.fx.viewer.IViewer;
import org.eclipse.gef.zest.examples.AbstractZestExample;
import org.eclipse.gef.zest.fx.ZestFxModule;
import org.eclipse.gef.zest.fx.ZestProperties;

import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.itemis.gef.tutorial.statechart.model.SimpleState;
import com.itemis.gef.tutorial.statechart.model.State;
import com.itemis.gef.tutorial.statechart.model.Statechart;
import com.itemis.gef.tutorial.statechart.model.Transition;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class StatechartZestExample extends AbstractZestExample {

	public static void main(String[] args) {
		Application.launch(args);
	}

	public StatechartZestExample() {
		super("Interactive statechart rendering using GEF Zest");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);

		String styleSheet = getClass().getResource("custom.css").toExternalForm();
		primaryStage.getScene().getStylesheets().add(styleSheet);
	}

	@Override
	protected Graph createGraph() {
		// convert statechart into graph
		Statechart statechart = Statechart.createExample();
		Graph.Builder builder = new Graph.Builder().attr(ZestProperties.LAYOUT_ALGORITHM__G,
				new RadialLayoutAlgorithm());

		for (State s : statechart.getStates()) {
			String label = s instanceof SimpleState ? ((SimpleState) s).getName() : s.getClass().getName();
			builder.node(s).attr(ZestProperties.LABEL__NE, label);
		}

		for (Transition t : statechart.getTransitions()) {
			builder.edge(t.getSource(), t.getTarget()).attr(ZestProperties.LABEL__NE, "");
		}

		return builder.build();
	}

	@Override
	protected Module createModule() {
		return new ZestFxModule() {
			@Override
			protected void bindIContentPartFactory() {
				binder().bind(new TypeLiteral<IContentPartFactory>() {
				}).to(StatechartContentPartFactory.class).in(AdaptableScopes.typed(IViewer.class));
			}
		};
	}
}
