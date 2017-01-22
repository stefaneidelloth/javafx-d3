package org.treez.javafxd3.plotly.layout.margin;

import org.treez.javafxd3.d3.core.JsEngine;

public class ZeroMargin extends Margin {


	//#region CONSTRUCTORS	
	
	public ZeroMargin(JsEngine engine) {
		super(engine);		
		setZeroMargins();
	}	

	//#end region
	
	//#region METHODS
	
	private void setZeroMargins() {
		setMember("l", 0);
		setMember("r", 0);
		setMember("t", 0);
		setMember("b", 0);
	}
	
	//#end region

	

}
