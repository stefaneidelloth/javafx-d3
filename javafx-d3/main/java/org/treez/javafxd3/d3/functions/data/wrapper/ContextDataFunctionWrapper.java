package org.treez.javafxd3.d3.functions.data.wrapper;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;

public class ContextDataFunctionWrapper<R> implements DataFunction<R> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainDataFunction<R, Element> plainDataFunction = null;

	//#end region

	//#region CONSTRUCTORS

	public ContextDataFunctionWrapper(WebEngine webEngine, PlainDataFunction<R, Element> plainDataFunction) {
		this.webEngine = webEngine;
		this.plainDataFunction = plainDataFunction;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object context, Object datum, int index) {
		Element convertedContext = ConversionUtil.convertObjectTo(context, Element.class, webEngine);
		return plainDataFunction.apply(convertedContext);
	}

	//#end region

}
