package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A DataFunction that returns true if the index is even
 */
public class EvenIndexDataFunction implements DataFunction<Boolean> {
	

	//#region METHODS

	@Override
	public Boolean apply(Object context, Object datum, int index) {		
		boolean isEven =  (index % 2) == 0;
		return isEven;
	}
	
	//#end region

}
