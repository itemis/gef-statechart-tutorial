package com.itemis.gef.tutorial.statechart.visuals;

import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Ellipse;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class FinalStateVisual extends Group {

	private GeometryNode<Ellipse> outerCircle;
	private GeometryNode<Ellipse> innerCircle;

	public FinalStateVisual() {
		// add circles
		outerCircle = new GeometryNode<Ellipse>(new Ellipse(-12, -12, 24, 24));
		outerCircle.setFill(Color.WHITE);
		outerCircle.setStroke(Color.BLACK);

		innerCircle = new GeometryNode<Ellipse>(new Ellipse(-9, -9, 18, 18));
		innerCircle.setFill(Color.BLACK);
		innerCircle.setStroke(null);

		getChildren().addAll(outerCircle, innerCircle);
	}

}
