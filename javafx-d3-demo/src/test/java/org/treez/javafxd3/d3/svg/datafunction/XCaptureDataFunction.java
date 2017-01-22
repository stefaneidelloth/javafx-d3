package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


public class XCaptureDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> xList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XCaptureDataFunction(JsEngine engine,  List<Double> xList){
		this.engine=engine;
		this.xList = xList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);					
	
		Double x = value.asCoords().x();
		xList.add(x);
		return x;
	}
	
	//#end region

}
