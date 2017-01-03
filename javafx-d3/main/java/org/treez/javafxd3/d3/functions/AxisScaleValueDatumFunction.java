package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.wrapper.Inspector;

import netscape.javascript.JSObject;

/**
 *  A datum function that extracts the value from a data object
 * and scales it with the given scale
 *  
 */
public class AxisScaleValueDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleValueDatumFunction(Scale<?> scale){
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;			
		Object valueObject = jsObject.eval("this.datum.value");			
		Double scaledValue = scale.applyForDouble(valueObject.toString());		
		return scaledValue;			
	}
	
	//#end region

}
