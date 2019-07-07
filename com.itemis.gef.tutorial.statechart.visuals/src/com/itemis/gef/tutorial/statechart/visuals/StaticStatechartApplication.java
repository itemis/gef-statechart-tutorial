package com.itemis.gef.tutorial.statechart.visuals;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class StaticStatechartApplication extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();

		// create states
		Node initialState = new InitialStateVisual();
		Node simpleState = new SimpleStateVisual("some longer label");
		Node finalState = new FinalStateVisual();

		// move states to the final position
		initialState.relocate(50, 50);
		simpleState.relocate(100, 100);
		finalState.relocate(200, 150);

		// create transition
		Node transition1 = createTransition(initialState, simpleState);
		Node transition2 = createTransition(simpleState, finalState);

		ObservableList<Node> children = root.getChildren();

		// add transitions
		children.addAll(transition1, transition2);

		// add states
		children.addAll(initialState, simpleState, finalState);

		primaryStage.setScene(new Scene(root, 440, 250));
		primaryStage.setTitle("GEF Statechart Tutorial");
		primaryStage.show();
	}

	private Node createTransition(Node startNode, Node endNode) {
		Bounds startNodeBounds = startNode.getBoundsInParent();
		Bounds endNodeBounds = endNode.getBoundsInParent();
		double startX = startNodeBounds.getMinX() + startNodeBounds.getWidth() / 2;
		double startY = startNodeBounds.getMinY() + startNodeBounds.getHeight() / 2;
		double endX = endNodeBounds.getMinX() + endNodeBounds.getWidth() / 2;
		double endY = endNodeBounds.getMinY() + endNodeBounds.getHeight() / 2;

		Line transition = new Line(startX, startY, endX, endY);
		return transition;
	}
}
