package com.itemis.gef.tutorial.statechart.parts;

import org.eclipse.gef.geometry.planar.Rectangle;

import com.itemis.gef.tutorial.statechart.model.SimpleState;
import com.itemis.gef.tutorial.statechart.visuals.SimpleStateVisual;

public class SimpleStatePart extends AbstractStatePart<SimpleStateVisual> {

	@Override
	protected SimpleStateVisual doCreateVisual() {
		return new SimpleStateVisual();
	}

	@Override
	protected void doRefreshVisual(SimpleStateVisual visual) {
		super.doRefreshVisual(visual);

		// refresh the label text from the model
		SimpleState state = getContent();
		visual.setText(state.getName());

		// refresh size from model
		Rectangle bounds = getContent().getBounds();
		visual.getShape().resize(bounds.getWidth(), bounds.getHeight());
	}

	@Override
	public SimpleState getContent() {
		return (SimpleState) super.getContent();
	}
}
