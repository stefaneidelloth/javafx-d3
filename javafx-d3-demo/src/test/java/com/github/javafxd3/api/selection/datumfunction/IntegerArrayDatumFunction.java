package com.github.javafxd3.api.selection.datumfunction;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the datum as integer array
 * 
 */
public class IntegerArrayDatumFunction implements DatumFunction<Integer[]> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public IntegerArrayDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Integer[] apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		
		Integer[] as = value.as();
		return as;
	}
	
	//#end region

}
