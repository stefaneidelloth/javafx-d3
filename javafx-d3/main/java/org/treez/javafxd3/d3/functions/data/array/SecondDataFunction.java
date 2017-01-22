package org.treez.javafxd3.d3.functions.data.array;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A datum function that returns the second array element as double
 *  
 */
public class SecondDataFunction implements DataFunction<Double> {
	
	private JsEngine engine;
		
	//#region CONSTRUCTORS	

	public SecondDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);		
		Object value = jsObject.eval("this[1]");	
		Double second = Double.parseDouble(value.toString());	
		return second;
	}
	
	//#end region

}
