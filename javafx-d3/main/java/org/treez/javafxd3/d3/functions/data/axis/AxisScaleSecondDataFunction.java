package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;

import netscape.javascript.JSObject;

public class AxisScaleSecondDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	QuantitativeScale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleSecondDataFunction(QuantitativeScale<?> scale){
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		
		Object secondValueObj = jsObject.eval("this.datum[1]");			
		Double scaledValue = scale.applyForDouble(secondValueObj.toString());		
		return scaledValue;		
	}
	
	//#end region

}
