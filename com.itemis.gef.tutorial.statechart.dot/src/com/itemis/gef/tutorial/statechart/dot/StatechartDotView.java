package com.itemis.gef.tutorial.statechart.dot;

import org.eclipse.gef.dot.internal.ui.DotGraphView;
import org.eclipse.gef.dot.internal.ui.DotGraphViewModule;
import org.eclipse.gef.zest.fx.ui.ZestFxUiModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;

@SuppressWarnings("restriction")
public class StatechartDotView extends DotGraphView {

	public StatechartDotView() {
		super();
		Injector injector = Guice.createInjector(Modules.override(new StatechartDotViewModule())
				.with(new ZestFxUiModule()));
		injector.injectMembers(this);
	}
}
