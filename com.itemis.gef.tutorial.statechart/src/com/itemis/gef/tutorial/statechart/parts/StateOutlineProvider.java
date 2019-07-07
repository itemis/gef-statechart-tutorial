package com.itemis.gef.tutorial.statechart.parts;

import org.eclipse.gef.common.adapt.IAdaptable;
import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.fx.utils.NodeUtils;
import org.eclipse.gef.geometry.planar.IGeometry;
import org.eclipse.gef.mvc.fx.providers.ShapeOutlineProvider;

public class StateOutlineProvider extends ShapeOutlineProvider {

	@Override
	public IGeometry get() {
		IAdaptable host = getAdaptable();

		GeometryNode<? extends IGeometry> outlineNode = null;

		if (host instanceof InitialStatePart) {
			InitialStatePart initialStatePart = (InitialStatePart) host;
			outlineNode = initialStatePart.getVisual().getCircle();
		} else if (host instanceof SimpleStatePart) {
			SimpleStatePart simpleStatePart = (SimpleStatePart) host;
			outlineNode = simpleStatePart.getVisual().getShape();
		} else if (host instanceof FinalStatePart) {
			FinalStatePart finalStatePart = (FinalStatePart) host;
			outlineNode = finalStatePart.getVisual().getOuterCircle();
		}

		if (outlineNode != null) {
			return getOutline(outlineNode);
		} else {
			return super.get();

		}
	}

	private IGeometry getOutline(GeometryNode<? extends IGeometry> outlineNode) {
		return NodeUtils.localToParent(outlineNode,
				NodeUtils.getResizedToShapeBounds(outlineNode, outlineNode.getGeometry()));
	}

}
