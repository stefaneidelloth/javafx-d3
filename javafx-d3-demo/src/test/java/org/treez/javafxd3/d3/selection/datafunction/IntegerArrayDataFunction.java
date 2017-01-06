package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the datum as integer array
 * 
 */
public class IntegerArrayDataFunction implements DataFunction<Integer[]> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public IntegerArrayDataFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Integer[] apply(Object context, Object datum, int index) {		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		int[] intArray = value.as();		
		Integer[] integerObjectArray = convertToIntegerArray(intArray);		
		return integerObjectArray;		
	}

	private Integer[] convertToIntegerArray(int[] intArray) {
		Integer[] integerArray = new Integer[intArray.length];
		int i = 0;
		for (int intValue : intArray) {
		    integerArray[i++] = Integer.valueOf(intValue);
		}
		return integerArray;
	}
	
	//#end region

}
