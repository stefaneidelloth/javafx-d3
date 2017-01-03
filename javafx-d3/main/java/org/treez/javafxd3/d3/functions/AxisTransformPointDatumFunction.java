package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.scales.QuantitativeScale;

import netscape.javascript.JSObject;

/**
 * A datum function that extracts an xy pair, scales the data with the
 * given scales and creates a transform string
 *  
 */
public class AxisTransformPointDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES
	
	QuantitativeScale<?> xScale;
	
	QuantitativeScale<?> yScale;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisTransformPointDatumFunction(QuantitativeScale<?> xScale, QuantitativeScale<?> yScale){
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		
		Object xValueObj = jsObject.eval("this.datum[0]");	
		Double x = Double.parseDouble(xValueObj.toString());	
		
		Object yValueObj = jsObject.eval("this.datum[1]");	
		Double y = Double.parseDouble(yValueObj.toString());
		
		Double scaledX = xScale.apply(x).asDouble();
		Double scaledY = yScale.apply(y).asDouble();
		
		String transformString = "translate("+scaledX+","+scaledY+")";
		return transformString;		
	}
	
	//#end region

}
