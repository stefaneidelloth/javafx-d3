package org.treez.javafxd3.d3.functions.data.array;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A datum function that returns the first array element as double
 *  
 */
public class FirstDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	
	public FirstDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		Object value = jsObject.eval("this[0]");	
		Double first = Double.parseDouble(value.toString());	
		return first;
	}
	
	//#end region

}
