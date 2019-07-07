package com.itemis.gef.tutorial.statechart.parts;

import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Dimension;
import org.eclipse.gef.geometry.planar.RoundedRectangle;
import org.eclipse.gef.mvc.fx.parts.IResizableContentPart;

import com.itemis.gef.tutorial.statechart.model.SimpleState;
import com.itemis.gef.tutorial.statechart.visuals.SimpleStateVisual;

public class SimpleStatePart extends AbstractStatePart<SimpleStateVisual>
		implements IResizableContentPart<SimpleStateVisual> {

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

		// use the IResizableContentPart API to resize the visual
		Dimension contentSize = getContentSize();
		setVisualSize(contentSize);

		// use the ITransformableContentPart API to position the visual
		setVisualTransform(getContentTransform());
	}

	@Override
	public SimpleState getContent() {
		return (SimpleState) super.getContent();
	}

	@Override
	public Dimension getContentSize() {
		return getContent().getBounds().getSize();
	}

	@Override
	public void setContentSize(Dimension totalSize) {
		getContent().getBounds().setSize(totalSize);
	}

	@Override
	public void setVisualSize(Dimension totalSize) {
		GeometryNode<RoundedRectangle> shape = getVisual().getShape();
		shape.resize(totalSize.getWidth(), totalSize.getHeight());
	}
}
