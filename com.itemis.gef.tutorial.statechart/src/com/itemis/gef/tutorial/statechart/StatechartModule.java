package com.itemis.gef.tutorial.statechart;

import org.eclipse.gef.common.adapt.AdapterKey;
import org.eclipse.gef.common.adapt.inject.AdapterMaps;
import org.eclipse.gef.mvc.fx.MvcFxModule;
import org.eclipse.gef.mvc.fx.handlers.FocusAndSelectOnClickHandler;
import org.eclipse.gef.mvc.fx.handlers.HoverOnHoverHandler;
import org.eclipse.gef.mvc.fx.handlers.ResizeTranslateFirstAnchorageOnHandleDragHandler;
import org.eclipse.gef.mvc.fx.handlers.TranslateSelectedOnDragHandler;
import org.eclipse.gef.mvc.fx.parts.DefaultHoverFeedbackPartFactory;
import org.eclipse.gef.mvc.fx.parts.DefaultSelectionFeedbackPartFactory;
import org.eclipse.gef.mvc.fx.parts.DefaultSelectionHandlePartFactory;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;
import org.eclipse.gef.mvc.fx.parts.SquareSegmentHandlePart;
import org.eclipse.gef.mvc.fx.policies.ResizePolicy;
import org.eclipse.gef.mvc.fx.policies.TransformPolicy;
import org.eclipse.gef.mvc.fx.providers.ShapeBoundsProvider;

import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.itemis.gef.tutorial.statechart.parts.AbstractStatePart;
import com.itemis.gef.tutorial.statechart.parts.SimpleStatePart;
import com.itemis.gef.tutorial.statechart.parts.StateAnchorProvider;
import com.itemis.gef.tutorial.statechart.parts.StateOutlineProvider;
import com.itemis.gef.tutorial.statechart.parts.StatechartContentPartFactory;

public class StatechartModule extends MvcFxModule {

	@Override
	protected void bindAbstractContentPartAdapters(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
		// TODO Auto-generated method stub
		super.bindAbstractContentPartAdapters(adapterMapBinder);

		// binding the HoverOnHoverHandler to every part
		// if a mouse is moving above a part it is set in the HoverModel
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(HoverOnHoverHandler.class);

		// add the focus and select handler to every part, listening to clicks
		// and changing the focus and selection model
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(FocusAndSelectOnClickHandler.class);
	}

	@Override
	protected void configure() {
		// start the default configuration
		super.configure();

		bindIContentPartFactory();

		// contents
		bindAbstractStatePartAdapters(AdapterMaps.getAdapterMapBinder(binder(), AbstractStatePart.class));
		bindSimpleStatePartAdapters(AdapterMaps.getAdapterMapBinder(binder(), SimpleStatePart.class));

		// handles
		bindSquareSegmentHandlePartAdapter(AdapterMaps.getAdapterMapBinder(binder(), SquareSegmentHandlePart.class));
	}

	private void bindIContentPartFactory() {
		binder().bind(new TypeLiteral<IContentPartFactory>() {
		}).toInstance(new StatechartContentPartFactory());
	}

	private void bindAbstractStatePartAdapters(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
		// geometry provider for selection feedback
		adapterMapBinder
				.addBinding(AdapterKey.role(DefaultSelectionFeedbackPartFactory.SELECTION_FEEDBACK_GEOMETRY_PROVIDER))
				.to(ShapeBoundsProvider.class);

		// geometry provider for hover feedback
		adapterMapBinder.addBinding(AdapterKey.role(DefaultHoverFeedbackPartFactory.HOVER_FEEDBACK_GEOMETRY_PROVIDER))
				.to(StateOutlineProvider.class);

		// bind anchor provider
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(StateAnchorProvider.class);

		// register transform policy
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(TransformPolicy.class);

		// register translate on drag
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(TranslateSelectedOnDragHandler.class);

	}

	private void bindSimpleStatePartAdapters(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
		// register selection handles for resizement
		adapterMapBinder
				.addBinding(AdapterKey.role(DefaultSelectionHandlePartFactory.SELECTION_HANDLES_GEOMETRY_PROVIDER))
				.to(ShapeBoundsProvider.class);

		// support resizing nodes
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(ResizePolicy.class);

	}

	/**
	 * Binds the parts of the selection handles (the squares in the corner) to
	 * handlers
	 *
	 * @param adapterMapBinder
	 */
	private void bindSquareSegmentHandlePartAdapter(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
		adapterMapBinder.addBinding(AdapterKey.defaultRole())
				.to(ResizeTranslateFirstAnchorageOnHandleDragHandler.class);
	}

}
