package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;

/**
 * Used by the LogScaleTest
 *
 */
public class LogScaleTestDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	JsEngine engine;
	
	//#end region
	
	
	//#region CONSTRUCTORS
	
	LogScaleTestDataFunction(JsEngine engine){
		this.engine = engine;
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
