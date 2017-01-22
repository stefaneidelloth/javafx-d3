package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 *  A datum function that extracts the value from a data object
 * and scales it with the given scale
 *  
 */
public class AxisScaleValueDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public AxisScaleValueDataFunction(JsEngine engine, Scale<?> scale){
		this.engine = engine;
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);			
		Object valueObject = jsObject.eval("this.value");			
		Double scaledValue = scale.applyForDouble(valueObject.toString());		
		return scaledValue;			
	}
	
	//#end region

}
