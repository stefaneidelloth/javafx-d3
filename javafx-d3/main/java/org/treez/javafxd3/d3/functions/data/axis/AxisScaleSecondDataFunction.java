package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class AxisScaleSecondDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private QuantitativeScale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public AxisScaleSecondDataFunction(JsEngine engine,QuantitativeScale<?> scale){
		this.engine = engine;
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		
		Object secondValueObj = jsObject.eval("this[1]");			
		Double scaledValue = scale.applyForDouble(secondValueObj.toString());		
		return scaledValue;		
	}
	
	//#end region

}
