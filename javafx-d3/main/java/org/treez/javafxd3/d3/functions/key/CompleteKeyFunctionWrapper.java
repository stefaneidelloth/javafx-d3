package org.treez.javafxd3.d3.functions.key;

import org.treez.javafxd3.d3.functions.KeyFunction;

public class CompleteKeyFunctionWrapper<R> implements KeyFunction<R> {

	//#region ATTRIBUTES

	private KeyFunction<R> wrappedFunction = null;

	//#end region

	//#region CONSTRUCTORS	

	public CompleteKeyFunctionWrapper(KeyFunction<R> wrappedFunction) {
		this.wrappedFunction = wrappedFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R call(Object context, Object newDataArray, Object datum, int index) {
		return wrappedFunction.call(context, newDataArray, datum, index);
	}

	//#end region

}
