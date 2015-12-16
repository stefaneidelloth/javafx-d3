package org.treez.javafxd3.d3.democases.xy;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.scales.LinearScale;

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
