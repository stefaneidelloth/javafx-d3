package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.functions.DataFunction;

public class CompleteDataFunctionWrapper<R> implements DataFunction<R> {

	//#region ATTRIBUTES

	private DataFunction<R> wrappedFunction = null;

	//#end region

	//#region CONSTRUCTORS	
	
	public CompleteDataFunctionWrapper(DataFunction<R> wrappedFunction) {
		this.wrappedFunction = wrappedFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {
		return wrappedFunction.apply(context, datum, index);
	}

	//#end region

}
