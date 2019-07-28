package com.itemis.gef.tutorial.statechart.dot;

import org.eclipse.gef.common.adapt.AdapterKey;
import org.eclipse.gef.dot.internal.ui.DotGraphViewModule;
import org.eclipse.gef.mvc.fx.policies.ResizePolicy;
import org.eclipse.gef.mvc.fx.policies.TransformPolicy;
import org.eclipse.gef.zest.fx.handlers.TranslateSelectedAndRelocateLabelsOnDragHandler;

import com.google.inject.Module;
import com.google.inject.multibindings.MapBinder;

public class StatechartDotViewModule extends DotGraphViewModule implements Module {

	@Override
	protected void bindNodePartAdapters(MapBinder<AdapterKey<?>, Object> adapterMapBinder) {
		super.bindNodePartAdapters(adapterMapBinder);

		// translate on-drag
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(TranslateSelectedAndRelocateLabelsOnDragHandler.class);

		// transform policy for relocation
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(TransformPolicy.class);

		// resize policy to resize nesting nodes
		adapterMapBinder.addBinding(AdapterKey.defaultRole()).to(ResizePolicy.class);
	}
}
