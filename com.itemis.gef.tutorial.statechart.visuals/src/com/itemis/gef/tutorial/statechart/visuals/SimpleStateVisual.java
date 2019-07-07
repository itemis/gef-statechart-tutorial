package com.itemis.gef.tutorial.statechart.visuals;

import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.RoundedRectangle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SimpleStateVisual extends Group {

	private static final double PADDING = 10;

	private GeometryNode<RoundedRectangle> shape;
	private Text label;

	public SimpleStateVisual() {
		// create shape
		shape = new GeometryNode<RoundedRectangle>(new RoundedRectangle(0, 0, 70, 30, 8, 8));
		shape.setStroke(Color.BLACK);
		shape.setFill(Color.LIGHTGREEN);

		// create label
		label = new Text();
		label.setTextOrigin(VPos.TOP);

		// resize shape when the label changes
		label.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Bounds labelBounds = label.getLayoutBounds();
				double newWidth = labelBounds.getWidth() + PADDING;
				double newHeight = labelBounds.getHeight() + PADDING;
				shape.resize(newWidth, newHeight);
			}

		});

		// stack label over shape
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(shape, label);

		getChildren().addAll(stackPane);
	}

	public SimpleStateVisual(String text) {
		this();
		setText(text);
	}

	public void setText(String text) {
		label.setText(text);
	}

	public GeometryNode<RoundedRectangle> getShape() {
		return shape;
	}

}
