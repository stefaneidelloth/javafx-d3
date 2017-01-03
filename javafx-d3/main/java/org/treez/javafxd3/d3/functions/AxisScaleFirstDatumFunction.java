package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.wrapper.Inspector;

import netscape.javascript.JSObject;

/**
 *  A datum function that extracts the first value from a data array
 * and scales it with the given scale
 *  
 */
public class AxisScaleFirstDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleFirstDatumFunction(Scale<?> scale){
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
