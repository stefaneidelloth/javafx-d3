package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class CompleteDataFunctionWrapper<R> implements DataFunction<R> {

	//#region ATTRIBUTES
	
	private JsEngine engine;

	private DataFunction<R> wrappedFunction = null;

	//#end region

	//#region CONSTRUCTORS	
	
	public CompleteDataFunctionWrapper(JsEngine engine, DataFunction<R> wrappedFunction) {
		this.engine = engine;
		this.wrappedFunction = wrappedFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {
		Object jsContext = engine.toJsObjectIfNotSimpleType(context);
		Object jsDatum = engine.toJsObjectIfNotSimpleType(datum);
		return wrappedFunction.apply(jsContext, jsDatum, index);
	}

	//#end region

}
