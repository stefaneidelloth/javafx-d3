package org.treez.javafxd3.d3.selection.keyfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.KeyFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A datum function that returns the datum as string
 * 
 */
public class IntegerKeyFunction implements KeyFunction<Integer> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public IntegerKeyFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);
		
		Integer as = value.as();
		return as;
	}	
	
	//#end region

}
