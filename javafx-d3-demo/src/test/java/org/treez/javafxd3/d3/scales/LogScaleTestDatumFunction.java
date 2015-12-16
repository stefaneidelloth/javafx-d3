package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;

/**
 * Used by the LogScaleTest
 *
 */
public class LogScaleTestDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES
	
	WebEngine webEngine;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	LogScaleTestDatumFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public String apply(Object context, Object d, int index) {
		
		System.out.println("Inside LogScaleTestDatumFunction");
				
		return "blah";
	}
	
	//#end regions

}
