package com.itemis.gef.tutorial.statechart.visuals;

import org.eclipse.gef.fx.nodes.Connection;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class TransitionVisual extends Connection {

	private static class ArrowHead extends Polyline {
		public ArrowHead() {
			super(15.0, 0.0, 10.0, 0.0, 10.0, 3.0, 0.0, 0.0, 10.0, -3.0, 10.0, 0.0);
			setFill(Color.BLACK);
		}
	}

	public TransitionVisual() {
		setEndDecoration(new ArrowHead());
	}
}
