package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.Scale;

import netscape.javascript.JSObject;

/**
 *  A datum function that extracts the first value from a data array
 * and scales it with the given scale
 *  
 */
public class AxisScaleFirstDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleFirstDataFunction(Scale<?> scale){
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;			
		Object firstValueObj = jsObject.eval("this.datum[0]");			
		Double scaledValue = scale.applyForDouble(firstValueObj.toString());		
		return scaledValue;			
	}
	
	//#end region

}
