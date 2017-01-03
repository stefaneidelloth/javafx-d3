package org.treez.javafxd3.plotly.layout.margin;

import javafx.scene.web.WebEngine;

public class ZeroMargin extends Margin {


	//#region CONSTRUCTORS	
	
	public ZeroMargin(WebEngine webEngine) {
		super(webEngine);		
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
