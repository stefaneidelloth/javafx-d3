package org.treez.javafxd3.d3.functions.data.array;

import org.treez.javafxd3.d3.functions.DataFunction;

import netscape.javascript.JSObject;

/**
 * A datum function that returns the second array element as double
 *  
 */
public class SecondDataFunction implements DataFunction<Double> {
		
	//#region CONSTRUCTORS	

	public SecondDataFunction(){		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;		
		Object value = jsObject.eval("this.datum[1]");	
		Double second = Double.parseDouble(value.toString());	
		return second;
	}
	
	//#end region

}
