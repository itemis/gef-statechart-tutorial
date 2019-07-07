package com.itemis.gef.tutorial.statechart.parts;

import org.eclipse.gef.common.adapt.IAdaptable;
import org.eclipse.gef.fx.anchors.DynamicAnchor;
import org.eclipse.gef.fx.anchors.IAnchor;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.inject.Provider;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.Node;

public class StateAnchorProvider implements Provider<IAnchor>, IAdaptable.Bound<IVisualPart<? extends Node>> {

	private IVisualPart<? extends Node> host;
	private IAnchor anchor;

	@Override
	public ReadOnlyObjectProperty<IVisualPart<? extends Node>> adaptableProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVisualPart<? extends Node> getAdaptable() {
		return host;
	}

	@Override
	public void setAdaptable(IVisualPart<? extends Node> adaptable) {
		host = adaptable;
	}

	@Override
	public IAnchor get() {
		if (anchor == null) {
			Node anchorage = getAdaptable().getVisual();
			anchor = new DynamicAnchor(anchorage);
		}
		return anchor;
	}

}
