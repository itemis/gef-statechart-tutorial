package com.itemis.gef.tutorial.statechart.visuals;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SimpleStateVisual extends Group {

	private static final double PADDING = 10;

	private Rectangle shape;
	private Text label;

	public SimpleStateVisual() {
		// create shape
		shape = new Rectangle(70, 30);
		shape.setStroke(Color.BLACK);
		shape.setFill(Color.LIGHTGREEN);
		shape.setArcWidth(15);
		shape.setArcHeight(15);

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
				shape.setWidth(newWidth);
				shape.setHeight(newHeight);
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

}
