package com.github.javafxd3.d3.democases.axis;

import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.functions.DatumFunction;
import com.github.javafxd3.d3.time.JsDate;
import com.github.javafxd3.d3.time.TimeScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class XAxisDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private TimeScale xScale;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XAxisDatumFunction(WebEngine webEngine, TimeScale xScale ){
		this.webEngine=webEngine;
		this.xScale = xScale;
	}
	
	//#end region

	//#region METHODS
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {
		
		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);
		JsDate date = value.as();
		Value outputValue = xScale.apply(date.getDate());
		Double result = outputValue.asDouble();
		return result;
	}
	
	//#end region
}
