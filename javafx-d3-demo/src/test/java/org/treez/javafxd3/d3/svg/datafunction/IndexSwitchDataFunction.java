package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A datum function that returns the datum as string
 * 
 */
public class IndexSwitchDataFunction implements DataFunction<Boolean> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public IndexSwitchDataFunction(){	
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Boolean apply(Object context, Object datum, int index) {		
		return index == 1 ? false : true;
	}
	
	//#end region

}
