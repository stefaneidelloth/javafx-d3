package org.treez.javafxd3.d3.selection.datumfunction;

import org.treez.javafxd3.d3.functions.DatumFunction;




public class OddEvenDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
		
	private double oddValue;
	private double evenValue;
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public OddEvenDatumFunction( double oddValue, double evenValue){
		this.oddValue = oddValue;
		this.evenValue = evenValue;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		Double result =  (index % 2) == 0 ? oddValue : evenValue;
		return result;
	}
	
	//#end region

}
