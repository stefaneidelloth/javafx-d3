package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


public class YCaptureDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> yList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YCaptureDataFunction(JsEngine engine,  List<Double> yList){
		this.engine=engine;
		this.yList = yList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);					
	
		Double y = value.asCoords().y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
