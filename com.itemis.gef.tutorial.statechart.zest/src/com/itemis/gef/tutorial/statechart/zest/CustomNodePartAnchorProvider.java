package com.itemis.gef.tutorial.statechart.zest;

import org.eclipse.gef.fx.anchors.DynamicAnchor;
import org.eclipse.gef.geometry.planar.IGeometry;
import org.eclipse.gef.zest.fx.providers.NodePartAnchorProvider;

import com.itemis.gef.tutorial.statechart.visuals.FinalStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.InitialStateVisual;

import javafx.scene.Node;

public class CustomNodePartAnchorProvider extends NodePartAnchorProvider {

	@Override
	protected IGeometry computeAnchorageReferenceGeometry(DynamicAnchor anchor) {
		Node anchorage = anchor.getAnchorage();

		if (anchorage instanceof InitialStateVisual) {
			InitialStateVisual initialStateVisual = (InitialStateVisual) anchorage;
			return initialStateVisual.getCircle().getGeometry();
		}

		if (anchorage instanceof FinalStateVisual) {
			FinalStateVisual finalStateVisual = (FinalStateVisual) anchorage;
			return finalStateVisual.getOuterCircle().getGeometry();
		}

		return super.computeAnchorageReferenceGeometry(anchor);
	}

}
