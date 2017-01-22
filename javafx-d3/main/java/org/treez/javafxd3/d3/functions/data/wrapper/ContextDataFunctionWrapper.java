package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

public class ContextDataFunctionWrapper<R> implements DataFunction<R> {

	//#region ATTRIBUTES

	private JsEngine engine = null;

	private PlainDataFunction<R, Element> plainDataFunction = null;

	//#end region

	//#region CONSTRUCTORS

	public ContextDataFunctionWrapper(JsEngine engine, PlainDataFunction<R, Element> plainDataFunction) {
		this.engine = engine;
		this.plainDataFunction = plainDataFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {		
		Element convertedContext = ConversionUtil.convertObjectTo(context, Element.class, engine);
		return plainDataFunction.apply(convertedContext);
	}

	//#end region

}
