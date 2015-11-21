package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;

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
