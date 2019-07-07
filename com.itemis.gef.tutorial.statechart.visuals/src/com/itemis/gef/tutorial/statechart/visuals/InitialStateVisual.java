package com.itemis.gef.tutorial.statechart.visuals;

import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Ellipse;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class InitialStateVisual extends Group {

	private GeometryNode<Ellipse> circle;

	public InitialStateVisual() {
		// add circle
		circle = new GeometryNode<Ellipse>(new Ellipse(-9, -9, 18, 18));
		circle.setFill(Color.BLACK);

		getChildren().addAll(circle);
	}
}
