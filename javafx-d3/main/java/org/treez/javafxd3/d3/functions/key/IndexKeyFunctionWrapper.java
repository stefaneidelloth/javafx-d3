package org.treez.javafxd3.d3.functions.key;

import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.PlainDataFunction;

public class IndexKeyFunctionWrapper<R, A> implements KeyFunction<R> {

	//#region ATTRIBUTES

	private PlainDataFunction<R, Integer> plainDataFunction = null;

	//#end region

	//#region CONSTRUCTORS

	public IndexKeyFunctionWrapper(PlainDataFunction<R, Integer> plainDataFunction) {
		this.plainDataFunction = plainDataFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R call(Object context, Object newDataArray, Object datum, int index) {
		return plainDataFunction.apply(index);
	}

	//#end region

}
