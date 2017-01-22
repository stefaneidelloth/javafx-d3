package org.treez.javafxd3.d3.functions.accessor;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.functions.data.wrapper.PlainDataFunction;

public class DsvObjectAccessorWrapper<R, A> implements DsvObjectAccessor<R> {

	//#region ATTRIBUTES

	private JsEngine engine = null;

	private PlainDataFunction<R, A> plainDataFunction = null;

	private Class<A> plainClass = null;

	//#end region

	//#region CONSTRUCTORS

	public DsvObjectAccessorWrapper(Class<A> classOfArgument, JsEngine engine,
			PlainDataFunction<R, A> plainDataFunction) {
		this.engine = engine;
		this.plainDataFunction = plainDataFunction;
		this.plainClass = classOfArgument;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object datum, int index) {		
		A convertedDatum = ConversionUtil.convertObjectTo(datum, plainClass, engine);
		return plainDataFunction.apply(convertedDatum);
	}

	//#end region

}
