package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;

/**
 * A datum function that returns the datum times a constant factor
 * 
 */
public class IndexFactorDatumFunction implements DatumFunction<Double> {

	//#region ATTRIBUTES

	Double factor;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 */
	public IndexFactorDatumFunction(Double factor) {
		this.factor = factor;
	}

	//#end region

	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		Double result = index * factor;
		return result;
	}

	//#end region

}
