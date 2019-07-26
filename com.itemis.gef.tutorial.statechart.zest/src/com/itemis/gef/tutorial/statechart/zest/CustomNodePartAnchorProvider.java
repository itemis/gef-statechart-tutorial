package com.itemis.gef.tutorial.statechart.zest;

import org.eclipse.gef.fx.anchors.DynamicAnchor;
import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.fx.utils.NodeUtils;
import org.eclipse.gef.geometry.planar.IGeometry;
import org.eclipse.gef.geometry.planar.RoundedRectangle;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;
import org.eclipse.gef.zest.fx.providers.NodePartAnchorProvider;

import com.itemis.gef.tutorial.statechart.visuals.FinalStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.InitialStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.SimpleStateVisual;

import javafx.scene.Node;

public class CustomNodePartAnchorProvider extends NodePartAnchorProvider {

	protected IGeometry computeAnchorageReferenceGeometry(
			DynamicAnchor anchor) {
		IVisualPart<? extends Node> host = getAdaptable();
		Node visual = host.getVisual();
		if (visual instanceof InitialStateVisual) {
			GeometryNode<? extends IGeometry> circle = ((InitialStateVisual) visual).getCircle();
			return NodeUtils.localToParent(circle, NodeUtils.getShapeOutline(circle));
		} else if (visual instanceof FinalStateVisual) {
			GeometryNode<? extends IGeometry> circle = ((FinalStateVisual) visual).getOuterCircle();
			return NodeUtils.localToParent(circle, NodeUtils.getShapeOutline(circle));
		} else if (visual instanceof SimpleStateVisual) {
			GeometryNode<RoundedRectangle> shape = ((SimpleStateVisual) visual).getShape();
			return NodeUtils.localToParent(shape, NodeUtils.getShapeOutline(shape));
		}
		return super.computeAnchorageReferenceGeometry(anchor);
	}
}
