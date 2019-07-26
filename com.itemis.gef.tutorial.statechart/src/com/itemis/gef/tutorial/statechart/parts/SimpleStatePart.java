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

		// use the IResizableContentPart API to resize the visual
		setVisualSize(getContentSize());
		
		// refresh the label text from the model
		SimpleState state = getContent();
		visual.setText(state.getName());
	}

	@Override
	public SimpleState getContent() {
		return (SimpleState) super.getContent();
	}
	
	@Override
	public Dimension getVisualSize() {
		GeometryNode<RoundedRectangle> shape = getVisual().getShape();
		return new Dimension(shape.getWidth(), shape.getHeight());
	}

	@Override
	public Dimension getContentSize() {
		return getContent().getBounds().getSize();
	}
	
	@Override
	public void setVisualSize(Dimension totalSize) {
		getVisual().getShape().resize(totalSize.width, totalSize.height);
	}

	@Override
	public void setContentSize(Dimension totalSize) {
		getContent().setBounds(getContent().getBounds().setSize(totalSize));
	}
}
