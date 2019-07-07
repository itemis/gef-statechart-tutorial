package com.itemis.gef.tutorial.statechart.visuals;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class InitialStateVisual extends Group {

	private Circle circle;

	public InitialStateVisual() {
		// add circle
		circle = new Circle(9, 9, 9);
		circle.setFill(Color.BLACK);

		getChildren().addAll(circle);
	}
}
