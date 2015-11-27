package com.github.javafxd3.demo.client.democases.xy;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.LinearScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class YAxisDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private LinearScale yScale;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YAxisDatumFunction(WebEngine webEngine, LinearScale yScale ){
		this.webEngine=webEngine;
		this.yScale = yScale;
	}
	
	//#end region

	//#region METHODS
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {
		
		JSObject datum = (JSObject) d;
		Value inputValue = new Value(webEngine, datum);
		
		Double input = inputValue.asDouble();
		Value value = yScale.apply(input);
		Double result = value.asDouble();
		return result;
		
		
		
		
		
		
	}
	
	//#end region
}
