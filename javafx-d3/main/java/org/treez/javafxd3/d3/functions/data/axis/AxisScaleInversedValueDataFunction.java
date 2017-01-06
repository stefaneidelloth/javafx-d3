package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.Scale;

import netscape.javascript.JSObject;

/**
 *  A datum function that extracts the value from a data object
 * and scales it with the given scale
 *  
 */
public class AxisScaleInversedValueDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private Scale<?> scale;	
	
	private double maxValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleInversedValueDataFunction(Scale<?> scale, double maxValue){
		this.scale = scale;		
		this.maxValue = maxValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;			
		Object valueObject = jsObject.eval("this.datum.value");			
		Double scaledValue = scale.applyForDouble(valueObject.toString());		
		Double inversedValue =  maxValue -scaledValue;
		return inversedValue;
	}
	
	//#end region

}
