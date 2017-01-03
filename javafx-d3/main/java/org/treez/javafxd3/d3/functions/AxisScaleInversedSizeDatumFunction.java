package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.scales.Scale;

import netscape.javascript.JSObject;

public class AxisScaleInversedSizeDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private Scale<?> scale;	
	
	private double maxValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public AxisScaleInversedSizeDatumFunction(Scale<?> scale, double maxValue){
		this.scale = scale;		
		this.maxValue = maxValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		
		Double value = Double.parseDouble(jsObject.eval("this.datum.value").toString());
		Double size = Double.parseDouble(jsObject.eval("this.datum.size").toString());				
		
		Double scaledRightValueInPx = scale.applyForDouble(""+ (value+size));	
		Double scaledLeftValueInPx = scale.applyForDouble("" + value);	
		Double sizeInPx = scaledRightValueInPx-scaledLeftValueInPx;			
		return - sizeInPx;		
	}
	
	//#end region

}
