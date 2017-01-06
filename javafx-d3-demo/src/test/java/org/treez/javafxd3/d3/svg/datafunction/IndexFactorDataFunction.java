package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A datum function that returns the datum times a constant factor
 * 
 */
public class IndexFactorDataFunction implements DataFunction<Double> {

	//#region ATTRIBUTES

	Double factor;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 */
	public IndexFactorDataFunction(Double factor) {
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
