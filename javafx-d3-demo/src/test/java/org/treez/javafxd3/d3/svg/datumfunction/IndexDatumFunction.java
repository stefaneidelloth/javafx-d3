package org.treez.javafxd3.d3.svg.datumfunction;

import org.treez.javafxd3.d3.functions.DatumFunction;

/**
 * A datum function that returns the index
 * 
 */
public class IndexDatumFunction implements DatumFunction<Integer> {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 */
	public IndexDatumFunction() {

	}

	//#end region

	//#region METHODS

	@Override
	public Integer apply(Object context, Object datum, int index) {
		return index;
	}

	//#end region

}
