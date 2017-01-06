package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A datum function that returns the index
 * 
 */
public class IndexDataFunction implements DataFunction<Integer> {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 */
	public IndexDataFunction() {

	}

	//#end region

	//#region METHODS

	@Override
	public Integer apply(Object context, Object datum, int index) {
		return index;
	}

	//#end region

}
