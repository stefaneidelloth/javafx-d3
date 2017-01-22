package org.treez.javafxd3.d3.functions.key;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.KeyFunction;

public class CompleteKeyFunctionWrapper<R> implements KeyFunction<R> {

	//#region ATTRIBUTES
	
	private JsEngine engine;

	private KeyFunction<R> wrappedFunction = null;

	//#end region

	//#region CONSTRUCTORS	

	public CompleteKeyFunctionWrapper(JsEngine engine, KeyFunction<R> wrappedFunction) {
		this.engine = engine;
		this.wrappedFunction = wrappedFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R call(Object context, Object newDataArray, Object datum, int index) {
		Object jsContext = engine.toJsObjectIfNotSimpleType(context);
		Object jsNewDataArray = engine.toJsObjectIfNotSimpleType(newDataArray);
		Object jsDatum = engine.toJsObjectIfNotSimpleType(datum);
		return wrappedFunction.call(jsContext, jsNewDataArray, jsDatum, index);
	}

	//#end region

}
