package org.treez.javafxd3.d3.functions.accessor;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.functions.data.wrapper.PlainDataFunction;

import javafx.scene.web.WebEngine;

public class DsvObjectAccessorWrapper<R, A> implements DsvObjectAccessor<R> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainDataFunction<R, A> plainDataFunction = null;

	private Class<A> plainClass = null;

	//#end region

	//#region CONSTRUCTORS

	public DsvObjectAccessorWrapper(Class<A> classOfArgument, WebEngine webEngine,
			PlainDataFunction<R, A> plainDataFunction) {
		this.webEngine = webEngine;
		this.plainDataFunction = plainDataFunction;
		this.plainClass = classOfArgument;
	}

	//#end region

	//#region METHODS

	@Override
	public R apply(Object data, int index) {
		A convertedDatum = ConversionUtil.convertObjectTo(data, plainClass, webEngine);
		return plainDataFunction.apply(convertedDatum);
	}

	//#end region

}
