package org.treez.javafxd3.d3.functions.data.array;

import org.treez.javafxd3.d3.functions.DataFunction;

import netscape.javascript.JSObject;

/**
 * A datum function that returns the first array element as double
 *  
 */
public class FirstDataFunction implements DataFunction<Double> {
	
	
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public FirstDataFunction(){		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		Object value = jsObject.eval("this.datum[0]");	
		Double first = Double.parseDouble(value.toString());	
		return first;
	}
	
	//#end region

}
