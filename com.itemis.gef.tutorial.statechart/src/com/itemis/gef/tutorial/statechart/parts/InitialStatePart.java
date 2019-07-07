package com.itemis.gef.tutorial.statechart.parts;

import com.itemis.gef.tutorial.statechart.visuals.InitialStateVisual;

public class InitialStatePart extends AbstractStatePart<InitialStateVisual> {

	@Override
	protected InitialStateVisual doCreateVisual() {
		return new InitialStateVisual();
	}

}
