package org.treez.javafxd3.d3.functions.key;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.PlainDataFunction;

import org.treez.javafxd3.d3.core.JsEngine;

public class KeyFunctionWrapper<R, A> implements KeyFunction<R> {

	//#region ATTRIBUTES

	private JsEngine engine=null;

	private PlainDataFunction<R, A> plainDataFunction = null;

	private Class<A> plainClass = null;

	private Runnable runnable = null;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Wraps an action that does not need any arguments and does not
	 * return a result
	 */
	public KeyFunctionWrapper(Runnable runnable) {
		this.runnable = runnable;
	}

	/**
	 * Wraps an action that only needs the datum as a single argument.
	 * The datum is automatically converted to the required type.
	 */
	public KeyFunctionWrapper(Class<A> classOfArgument, JsEngine engine,
			PlainDataFunction<R, A> plainDataFunction) {
		this.engine = engine;
		this.plainDataFunction = plainDataFunction;
		this.plainClass = classOfArgument;
	}

	//#end region

	//#region METHODS

	@Override
	public R call(Object context, Object newDataArray, Object datum, int index){
		if (runnable != null) {
			runnable.run();
			return null;
		} else {
			A convertedDatum = ConversionUtil.convertObjectTo(datum, plainClass, engine);
			return plainDataFunction.apply(convertedDatum);
		}
	}

	//#end region

}
