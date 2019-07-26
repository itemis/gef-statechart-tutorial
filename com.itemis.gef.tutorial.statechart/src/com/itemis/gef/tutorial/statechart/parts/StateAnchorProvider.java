package com.itemis.gef.tutorial.statechart.parts;

import org.eclipse.gef.common.adapt.IAdaptable;
import org.eclipse.gef.fx.anchors.DynamicAnchor;
import org.eclipse.gef.fx.anchors.DynamicAnchor.AnchorageReferenceGeometry;
import org.eclipse.gef.fx.anchors.IAnchor;
import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.fx.utils.NodeUtils;
import org.eclipse.gef.geometry.planar.IGeometry;
import org.eclipse.gef.geometry.planar.RoundedRectangle;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.inject.Provider;
import com.itemis.gef.tutorial.statechart.visuals.FinalStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.InitialStateVisual;
import com.itemis.gef.tutorial.statechart.visuals.SimpleStateVisual;

import javafx.beans.binding.ObjectBinding;
import javafx.scene.Node;

public class StateAnchorProvider extends IAdaptable.Bound.Impl<IVisualPart<? extends Node>> implements Provider<IAnchor> {

	private DynamicAnchor anchor;

	@Override
	public IAnchor get() {
		if (anchor == null) {
			Node anchorage = getAdaptable().getVisual();
			anchor = new DynamicAnchor(anchorage);
			anchor.getComputationParameter(AnchorageReferenceGeometry.class)
			.bind(new ObjectBinding<IGeometry>() {
				{
					// XXX: Binding value needs to be recomputed when the
					// anchorage changes or when the layout bounds of the
					// respective anchorage changes.
					anchor.anchorageProperty()
							.addListener((obs, ov, nv) -> {
									if (ov != null) {
										unbind(ov.boundsInLocalProperty());
									}
									if (nv != null) {
										bind(nv.boundsInLocalProperty());
									}
									invalidate();
								});
					Node anchorage = anchor.getAnchorage();
					if (anchorage != null) {
						bind(anchorage.boundsInLocalProperty());
					}
				}

				@Override
				protected IGeometry computeValue() {
					return computeAnchorageReferenceGeometry(anchor);
				}
			});
		}
		return anchor;
	}
	
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
		return NodeUtils.getShapeOutline(visual);
	}
}
