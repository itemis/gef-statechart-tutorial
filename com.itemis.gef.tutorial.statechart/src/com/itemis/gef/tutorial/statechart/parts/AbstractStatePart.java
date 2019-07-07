package com.itemis.gef.tutorial.statechart.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.geometry.planar.Rectangle;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.ITransformableContentPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.itemis.gef.tutorial.statechart.model.State;

import javafx.scene.Node;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;

public abstract class AbstractStatePart<T extends Node> extends AbstractContentPart<T>
		implements ITransformableContentPart<T> {

	@Override
	public State getContent() {
		return (State) super.getContent();
	}

	@Override
	protected void doRefreshVisual(T visual) {
		// relocate to model position
		Rectangle bounds = getContent().getBounds();
		Affine affine = getAdapter(ITransformableContentPart.TRANSFORM_PROVIDER_KEY).get();
		affine.setTx(bounds.getX());
		affine.setTy(bounds.getY());
	}

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		return HashMultimap.create();
	}

	@Override
	protected List<? extends Object> doGetContentChildren() {
		return Collections.emptyList();
	}

	@Override
	public Affine getContentTransform() {
		Rectangle bounds = getContent().getBounds();
		return new Affine(new Translate(bounds.getX(), bounds.getY()));
	}

	@Override
	public void setContentTransform(Affine totalTransform) {
		// storing the new position
		Rectangle bounds = getContent().getBounds().getCopy();
		bounds.setX(totalTransform.getTx());
		bounds.setY(totalTransform.getTy());
		getContent().setBounds(bounds);
	}
}
