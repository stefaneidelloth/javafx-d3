package org.treez.javafxd3.d3.svg.datumfunction;

import org.treez.javafxd3.d3.functions.DatumFunction;

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
