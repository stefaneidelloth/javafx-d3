package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.functions.DataFunction;

public class IndexDataFunctionWrapper<R, A> implements DataFunction<R> {

	//#region ATTRIBUTES
	
	private PlainDataFunction<R, Integer> plainDataFunction = null;

	//#end region

	//#region CONSTRUCTORS

	public IndexDataFunctionWrapper(PlainDataFunction<R, Integer> plainDataFunction) {	
		this.plainDataFunction = plainDataFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {		
		return plainDataFunction.apply(index);
	}

	//#end region

}
