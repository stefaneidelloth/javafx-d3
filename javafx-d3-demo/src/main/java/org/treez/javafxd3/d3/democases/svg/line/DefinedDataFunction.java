package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class DefinedDataFunction implements DataFunction<Boolean> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DefinedDataFunction(JsEngine engine){
		this.engine = engine;
	}
	
	//#end region
	
	//#region METHODS	
	
	@Override
	public Boolean apply(Object context, Object d, int index) {
		
		JsObject datum = (JsObject) engine.toJsObjectIfNotSimpleType(d);		
		Value value = new Value(engine, datum);
		
		CustomCoords coords = value.<CustomCoords> as(CustomCoords.class);
		if (coords!=null){
			boolean defined = coords.defined();
			return defined;
		}
			return false;
		
	}
	
	public Boolean apply(String context, String d, int index){
		return null;
	}
	
	//#end region
}