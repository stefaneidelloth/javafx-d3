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
public class AxisScaleInversedValueDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private Scale<?> scale;	
	
	private double maxValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public AxisScaleInversedValueDataFunction(JsEngine engine,Scale<?> scale, double maxValue){
		this.engine = engine;
		this.scale = scale;		
		this.maxValue = maxValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);			
		Object valueObject = jsObject.eval("this.value");			
		Double scaledValue = scale.applyForDouble(valueObject.toString());		
		Double inversedValue =  maxValue -scaledValue;
		return inversedValue;
	}
	
	//#end region

}
