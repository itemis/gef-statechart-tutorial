package com.itemis.gef.tutorial.statechart.parts;

import com.itemis.gef.tutorial.statechart.visuals.FinalStateVisual;

public class FinalStatePart extends AbstractStatePart<FinalStateVisual> {

	@Override
	protected FinalStateVisual doCreateVisual() {
		return new FinalStateVisual();
	}

}
