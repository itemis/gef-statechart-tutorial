package com.itemis.gef.tutorial.statechart.visuals;

import org.eclipse.gef.fx.anchors.DynamicAnchor;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

		// make states draggable
		makeDraggable(initialState);
		makeDraggable(simpleState);
		makeDraggable(finalState);

		primaryStage.setScene(new Scene(root, 440, 250));
		primaryStage.setTitle("GEF Statechart Tutorial");
		primaryStage.show();
	}

	private Node createTransition(Node startNode, Node endNode) {
		TransitionVisual transition = new TransitionVisual();
		DynamicAnchor startAnchor = new DynamicAnchor(startNode);
		DynamicAnchor endAnchor = new DynamicAnchor(endNode);
		transition.setStartAnchor(startAnchor);
		transition.setEndAnchor(endAnchor);

		return transition;
	}

	private void makeDraggable(final Node node) {
		EventHandler<MouseEvent> dragNodeHandler = new EventHandler<MouseEvent>() {

			private Point2D initialMouseLocation;
			private Point2D initialTranslation;

			@Override
			public void handle(MouseEvent event) {
				if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
					// save the initial mouse location and initial node translation when the node is
					// pressed
					initialMouseLocation = new Point2D(event.getSceneX(), event.getSceneY());
					initialTranslation = new Point2D(node.getTranslateX(), node.getTranslateY());
				} else {
					// move the node by the mouse offset
					double mouseLocationDeltaX = event.getSceneX() - initialMouseLocation.getX();
					double mouseLocationDeltaY = event.getSceneY() - initialMouseLocation.getY();
					node.setTranslateX(initialTranslation.getX() + mouseLocationDeltaX);
					node.setTranslateY(initialTranslation.getY() + mouseLocationDeltaY);
				}
			}

		};

		node.addEventHandler(MouseEvent.MOUSE_PRESSED, dragNodeHandler);
		node.addEventHandler(MouseEvent.MOUSE_DRAGGED, dragNodeHandler);
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, dragNodeHandler);
	}
}
