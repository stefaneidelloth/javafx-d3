package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.ChordDef;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class ChordRadiusDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public ChordRadiusDataFunction(JsEngine engine){
		this.engine=engine;
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);
		Double radius = value.<ChordDef> as().radius;
		System.out.println("radius: " + radius);
		return radius;
	}
	
	//#end region

}
