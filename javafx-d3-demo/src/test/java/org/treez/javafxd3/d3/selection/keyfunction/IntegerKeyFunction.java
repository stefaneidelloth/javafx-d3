package org.treez.javafxd3.d3.selection.keyfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.KeyFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the datum as string
 * 
 */
public class IntegerKeyFunction implements KeyFunction<Integer> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public IntegerKeyFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		
		Integer as = value.as();
		return as;
	}	
	
	//#end region

}
