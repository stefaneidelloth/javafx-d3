package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class YCoordsCounterDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> yList;
	
	private Coords counter;
	
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YCoordsCounterDataFunction(JsEngine engine,  List<Double> yList, Coords counter){
		this.engine=engine;
		this.yList = yList;
		this.counter = counter;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Value value = new Value(engine, jsObject);					
	
		counter.y(counter.y() + 1);
		
		Double y = value.asCoords().y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
