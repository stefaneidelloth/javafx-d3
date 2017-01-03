package org.treez.javafxd3.d3.functions;

import netscape.javascript.JSObject;

/**
 * A datum function that returns the first array element as double
 *  
 */
public class FirstDatumFunction implements DatumFunction<Double> {
	
	
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public FirstDatumFunction(){		
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
