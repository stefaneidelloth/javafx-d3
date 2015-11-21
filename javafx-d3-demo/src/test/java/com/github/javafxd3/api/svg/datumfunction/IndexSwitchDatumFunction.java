package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;

/**
 * A datum function that returns the datum as string
 * 
 */
public class IndexSwitchDatumFunction implements DatumFunction<Boolean> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public IndexSwitchDatumFunction(){	
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Boolean apply(Object context, Object datum, int index) {		
		return index == 1 ? false : true;
	}
	
	//#end region

}
