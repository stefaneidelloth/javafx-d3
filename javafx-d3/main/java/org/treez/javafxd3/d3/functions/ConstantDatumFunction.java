package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.functions.DatumFunction;

/**
 * A DatumFunction that returns a constant value
 */
public class ConstantDatumFunction<T> implements DatumFunction<T> {
	
	//#region ATTRIBUTES
	
	T constantValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public ConstantDatumFunction(T constantValue){
		this.constantValue = constantValue;
	}
	//#end region
	
	//#region METHODS

	@Override
	public T apply(Object context, Object datum, int index) {		
		return constantValue;
	}
	
	//#end region

}
