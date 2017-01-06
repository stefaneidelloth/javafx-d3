package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;

import netscape.javascript.JSObject;

public class ColorScaleLevelDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	QuantitativeScale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS

	public ColorScaleLevelDataFunction(QuantitativeScale<?> scale){
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {			
		JSObject jsObject = (JSObject) datum;
		Object levelObject = jsObject.eval("this.datum.level");		
		String scaledValue = scale.applyForString(levelObject.toString());		
		return scaledValue;			
	}
	
	//#end region

}
