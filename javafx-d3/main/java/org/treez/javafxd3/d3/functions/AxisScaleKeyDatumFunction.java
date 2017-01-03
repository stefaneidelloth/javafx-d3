package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.scales.Scale;

import netscape.javascript.JSObject;

/**
 *  A datum function that extracts the key value from a data object
 * and scales it with the given scale
 *  
 */
public class AxisScaleKeyDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleKeyDatumFunction(Scale<?> scale){
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {		
		JSObject jsObject = (JSObject) datum;			
		Object firstValueObj = jsObject.eval("this.datum.key");			
		Double scaledValue = scale.applyForDouble(firstValueObj.toString());		
		return scaledValue;			
	}
	
	//#end region

}
