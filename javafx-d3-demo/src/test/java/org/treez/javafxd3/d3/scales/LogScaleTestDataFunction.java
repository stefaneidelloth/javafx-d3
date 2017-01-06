package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;

/**
 * Used by the LogScaleTest
 *
 */
public class LogScaleTestDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	WebEngine webEngine;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	LogScaleTestDataFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public String apply(Object context, Object d, int index) {
		
		System.out.println("Inside LogScaleTestDataFunction");
				
		return "blah";
	}
	
	//#end regions

}
