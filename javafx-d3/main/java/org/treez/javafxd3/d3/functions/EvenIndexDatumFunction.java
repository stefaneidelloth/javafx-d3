package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.functions.DatumFunction;

/**
 * A DatumFunction that returns true if the index is even
 */
public class EvenIndexDatumFunction implements DatumFunction<Boolean> {
	

	//#region METHODS

	@Override
	public Boolean apply(Object context, Object datum, int index) {		
		boolean isEven =  (index % 2) == 0;
		return isEven;
	}
	
	//#end region

}
