package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


public class AxisScaleInversedFirstDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private QuantitativeScale<?> scale;	
	
	private double maxValue;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AxisScaleInversedFirstDataFunction(JsEngine engine, QuantitativeScale<?> scale, double maxValue){
		this.engine=engine;
		this.scale = scale;		
		this.maxValue = maxValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		
		Object secondValueObj = jsObject.eval("this[0]");	
		Double secondValue = Double.parseDouble(secondValueObj.toString());		
		
		Double scaledValue = scale.apply(secondValue).asDouble();
		double inversedValue = maxValue-scaledValue;
		return inversedValue;			
	}
	
	//#end region

}
