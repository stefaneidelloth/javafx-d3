package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A datum function that returns the datum as string
 * 
 */
public class StringDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public StringDataFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		JsObject jsDatum = (JsObject) engine.toJsObjectIfNotSimpleType(datum);		
		Value value = new Value(engine,  jsDatum);
		String stringValue = value.asString();
		return stringValue;
	}
	
	//#end region

}
