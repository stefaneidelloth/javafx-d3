package org.treez.javafxd3.d3.functions.data.axis;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A datum function that extracts an xy pair, scales the data with the
 * given scales and creates a transform string
 *  
 */
public class AxisTransformPointDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private QuantitativeScale<?> xScale;
	
	private QuantitativeScale<?> yScale;
	
	//#end region
	
	//#region CONSTRUCTORS	

	public AxisTransformPointDataFunction(JsEngine engine, QuantitativeScale<?> xScale, QuantitativeScale<?> yScale){
		this.engine = engine;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		
		Object xValueObj = jsObject.eval("this[0]");	
		Double x = Double.parseDouble(xValueObj.toString());	
		
		Object yValueObj = jsObject.eval("this[1]");	
		Double y = Double.parseDouble(yValueObj.toString());
		
		Double scaledX = xScale.apply(x).asDouble();
		Double scaledY = yScale.apply(y).asDouble();
		
		String transformString = "translate("+scaledX+","+scaledY+")";
		return transformString;		
	}
	
	//#end region

}
