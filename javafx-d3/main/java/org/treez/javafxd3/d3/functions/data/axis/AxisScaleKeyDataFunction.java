package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.Scale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 *  A datum function that extracts the key value from a data object
 * and scales it with the given scale
 *  
 */
public class AxisScaleKeyDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private Scale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	
	public AxisScaleKeyDataFunction(JsEngine engine, Scale<?> scale){
		this.engine = engine;
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);			
		Object firstValueObj = jsObject.eval("this.key");			
		Double scaledValue = scale.applyForDouble(firstValueObj.toString());		
		return scaledValue;			
	}
	
	//#end region

}
