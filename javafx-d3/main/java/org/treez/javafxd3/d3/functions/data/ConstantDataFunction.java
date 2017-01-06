package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;

/**
 * A DataFunction that returns a constant value
 */
public class ConstantDataFunction<T> implements DataFunction<T> {
	
	//#region ATTRIBUTES
	
	T constantValue;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public ConstantDataFunction(T constantValue){
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
