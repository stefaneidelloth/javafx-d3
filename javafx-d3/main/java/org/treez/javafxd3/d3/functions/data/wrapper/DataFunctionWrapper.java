package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class DataFunctionWrapper<R, A> implements DataFunction<R> {

	//#region ATTRIBUTES

	private JsEngine engine = null;

	private PlainDataFunction<R, A> plainDataFunction = null;

	private Class<A> plainClass = null;

	private Runnable runnable = null;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Wraps an action that does not need any arguments and does not return a
	 * result
	 */
	public DataFunctionWrapper(Runnable runnable) {
		this.runnable = runnable;
	}

	/**
	 * Wraps an action that only needs the datum as a single argument. The datum
	 * is automatically converted to the required type.
	 */
	public DataFunctionWrapper(Class<A> classOfArgument, JsEngine engine,
			PlainDataFunction<R, A> plainDataFunction) {
		this.engine = engine;
		this.plainDataFunction = plainDataFunction;
		this.plainClass = classOfArgument;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {
			
		if (runnable != null) {
			runnable.run();
			return null;
		} else {
			A convertedDatum = ConversionUtil.convertObjectTo(datum, plainClass, engine);
			try {
				return plainDataFunction.apply(convertedDatum);
			} catch (Exception exception) {
				String message = "Could not execute wrapped data function!";
				System.out.println("DataFunctionWrapper: " + message);
				exception.printStackTrace();
				throw new IllegalStateException(message, exception);
			}
		}
	}

	//#end region

}
