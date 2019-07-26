package com.itemis.gef.tutorial.statechart.zest;

import org.eclipse.gef.common.adapt.AdapterKey;
import org.eclipse.gef.common.adapt.inject.AdaptableScopes;
import org.eclipse.gef.graph.Graph;
import org.eclipse.gef.layout.algorithms.RadialLayoutAlgorithm;
import org.eclipse.gef.mvc.fx.handlers.ConnectedSupport;
import org.eclipse.gef.mvc.fx.handlers.HoverOnHoverHandler;
import org.eclipse.gef.mvc.fx.parts.DefaultFocusFeedbackPartFactory;
import org.eclipse.gef.mvc.fx.parts.DefaultHoverFeedbackPartFactory;
import org.eclipse.gef.mvc.fx.parts.DefaultSelectionFeedbackPartFactory;
import org.eclipse.gef.mvc.fx.parts.DefaultSelectionHandlePartFactory;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;
import org.eclipse.gef.mvc.fx.policies.ResizePolicy;
import org.eclipse.gef.mvc.fx.policies.TransformPolicy;
import org.eclipse.gef.mvc.fx.providers.ShapeBoundsProvider;
import org.eclipse.gef.mvc.fx.viewer.IViewer;
import org.eclipse.gef.zest.examples.AbstractZestExample;
import org.eclipse.gef.zest.fx.ZestFxModule;
import org.eclipse.gef.zest.fx.ZestProperties;
import org.eclipse.gef.zest.fx.behaviors.NodeHidingBehavior;
import org.eclipse.gef.zest.fx.behaviors.NodeLayoutBehavior;
import org.eclipse.gef.zest.fx.handlers.HideOnTypeHandler;
import org.eclipse.gef.zest.fx.handlers.OpenNestedGraphOnDoubleClickHandler;
import org.eclipse.gef.zest.fx.handlers.ShowHiddenNeighborsOnTypeHandler;
import org.eclipse.gef.zest.fx.handlers.TranslateSelectedAndRelocateLabelsOnDragHandler;
import org.eclipse.gef.zest.fx.policies.HidePolicy;
import org.eclipse.gef.zest.fx.policies.ShowHiddenNeighborsPolicy;
import org.eclipse.gef.zest.fx.providers.NodePartAnchorProvider;

import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
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

			/**
			 * binding {@link CustomNodePartAnchorProvider} instead of the
			 * {@link NodePartAnchorProvider}.
			 */
			@Override
			protected void bindNodePartAdapters(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
				// layout
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(NodeLayoutBehavior.class);
				// pruning
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(HidePolicy.class);
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(ShowHiddenNeighborsPolicy.class);
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(NodeHidingBehavior.class);

				// translate on-drag
				adapterMapBinder.addBinding(AdapterKey.defaultRole())
						.to(TranslateSelectedAndRelocateLabelsOnDragHandler.class);

				// show hidden neighbors on-type
				adapterMapBinder.addBinding(AdapterKey.role("show-hidden-neighbors"))
						.to(ShowHiddenNeighborsOnTypeHandler.class);

				// hide on-type
				adapterMapBinder.addBinding(AdapterKey.role("hide")).to(HideOnTypeHandler.class);

				adapterMapBinder.addBinding(AdapterKey.role("open-nested-graph"))
						.to(OpenNestedGraphOnDoubleClickHandler.class);

				// transform policy for relocation
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(TransformPolicy.class);

				// resize policy to resize nesting nodes
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(ResizePolicy.class);

				// anchor provider
				adapterMapBinder.addBinding(AdapterKey.defaultRole())
						.to(/* NodePartAnchorProvider.class */CustomNodePartAnchorProvider.class);

				// feedback and handles
				adapterMapBinder
						.addBinding(
								AdapterKey.role(DefaultSelectionHandlePartFactory.SELECTION_HANDLES_GEOMETRY_PROVIDER))
						.toProvider(new Provider<ShapeBoundsProvider>() {
							@Override
							public ShapeBoundsProvider get() {
								return new ShapeBoundsProvider(1);
							}
						});

				// selection feedback provider
				adapterMapBinder
						.addBinding(AdapterKey
								.role(DefaultSelectionFeedbackPartFactory.SELECTION_FEEDBACK_GEOMETRY_PROVIDER))
						.toProvider(new Provider<ShapeBoundsProvider>() {
							@Override
							public ShapeBoundsProvider get() {
								return new ShapeBoundsProvider(0.5);
							}
						});

				// selection link feedback provider
				adapterMapBinder
						.addBinding(AdapterKey
								.role(DefaultSelectionFeedbackPartFactory.SELECTION_LINK_FEEDBACK_GEOMETRY_PROVIDER))
						.toProvider(new Provider<ShapeBoundsProvider>() {
							@Override
							public ShapeBoundsProvider get() {
								return new ShapeBoundsProvider(0.5);
							}
						});

				// hover feedback provider
				adapterMapBinder
						.addBinding(AdapterKey.role(DefaultHoverFeedbackPartFactory.HOVER_FEEDBACK_GEOMETRY_PROVIDER))
						.to(ShapeBoundsProvider.class);

				// focus feedback provider
				adapterMapBinder
						.addBinding(AdapterKey.role(DefaultFocusFeedbackPartFactory.FOCUS_FEEDBACK_GEOMETRY_PROVIDER))
						.toProvider(new Provider<ShapeBoundsProvider>() {
							@Override
							public ShapeBoundsProvider get() {
								return new ShapeBoundsProvider(0.5);
							}
						});

				// hover on-hover
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(HoverOnHoverHandler.class);

				// normalize on drag
				adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(ConnectedSupport.class);
			}
		};
	}
}
