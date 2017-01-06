package org.treez.javafxd3.d3.democases.xy;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.LinearScale;

public class XAxisDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private LinearScale xScale;
	
	private double[] xData;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XAxisDataFunction(LinearScale xScale, double[] xData){		
		this.xScale = xScale;
		this.xData = xData;
	}
	
	//#end region

	//#region METHODS
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {
						
		Double x = xData[index];
		Value value = xScale.apply(x);
		Double result = value.asDouble();
		return result;		
	}
	
	//#end region
}
