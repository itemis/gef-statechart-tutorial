package com.itemis.gef.tutorial.statechart;

import org.eclipse.gef.mvc.examples.AbstractMvcExample;

import com.google.inject.Module;
import com.itemis.gef.tutorial.statechart.model.Statechart;

import javafx.application.Application;

@SuppressWarnings("restriction")
public class StatechartMvcExample extends AbstractMvcExample {

	public static void main(String args[]) {
		Application.launch(args);
	}

	public StatechartMvcExample() {
		super("GEF Statechart MVC Example");
	}

	@Override
	protected Module createModule() {
		return new StatechartModule();
	}

	@Override
	protected void populateViewerContents() {
		Statechart statechart = Statechart.createExample();
		getContentViewer().getContents().setAll(statechart);
	}

}
