package com.itemis.gef.tutorial.statechart.visuals;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FinalStateVisual extends Group {

	private Circle outerCircle;
	private Circle innerCircle;

	public FinalStateVisual() {
		// add circles
		outerCircle = new Circle(12, 12, 12);
		outerCircle.setFill(Color.WHITE);
		outerCircle.setStroke(Color.BLACK);

		innerCircle = new Circle(12, 12, 9);
		innerCircle.setFill(Color.BLACK);
		innerCircle.setStroke(null);

		getChildren().addAll(outerCircle, innerCircle);
	}

}
