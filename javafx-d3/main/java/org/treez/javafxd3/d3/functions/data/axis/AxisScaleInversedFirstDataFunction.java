package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;

import netscape.javascript.JSObject;


public class AxisScaleInversedFirstDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private QuantitativeScale<?> scale;	
	
	private double maxValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleInversedFirstDataFunction(QuantitativeScale<?> scale, double maxValue){
		this.scale = scale;		
		this.maxValue = maxValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		
		Object secondValueObj = jsObject.eval("this.datum[0]");	
		Double secondValue = Double.parseDouble(secondValueObj.toString());		
		
		Double scaledValue = scale.apply(secondValue).asDouble();
		double inversedValue = maxValue-scaledValue;
		return inversedValue;			
	}
	
	//#end region

}
