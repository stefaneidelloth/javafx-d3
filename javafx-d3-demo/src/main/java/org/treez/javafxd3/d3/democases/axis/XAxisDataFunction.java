package org.treez.javafxd3.d3.democases.axis;

import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeScale;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class XAxisDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private TimeScale xScale;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XAxisDataFunction(WebEngine webEngine, TimeScale xScale ){
		this.webEngine=webEngine;
		this.xScale = xScale;
	}
	
	//#end region

	//#region METHODS
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {
		
		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);
		JSObject jsDate = value.as();
		
		JsDate date = new JsDate(webEngine, jsDate);
		Value outputValue = xScale.apply(date.getDate());
		Double result = outputValue.asDouble();
		return result;
	}
	
	//#end region
}
