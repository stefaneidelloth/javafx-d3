package org.treez.javafxd3.d3.arrays.foreach;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.data.wrapper.PlainDataFunction;

import javafx.scene.web.WebEngine;

/**
 * Wraps a ForEachCallback that only needs the element as argument. The type of
 * the element is converted to the required type A
 */
public class ForEachCallbackWrapper<R, A> implements ForEachCallback<R> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainDataFunction<R, A> plainDataFunction = null;

	private Class<A> plainClass = null;

	//#end region

	//#region CONSTRUCTORS

	public ForEachCallbackWrapper(Class<A> classOfArgument, WebEngine webEngine,
			PlainDataFunction<R, A> plainDataFunction) {
		this.webEngine = webEngine;
		this.plainDataFunction = plainDataFunction;
		this.plainClass = classOfArgument;
	}

	//#end region

	//#region METHODS

	@Override
	public R forEach(Object context, Object arrayElement, int index, Object array) {
		A data = ConversionUtil.convertObjectTo(arrayElement, plainClass, webEngine);
		if (data != null) {
			return plainDataFunction.apply(data);
		} else {
			return null;
		}
	}

	//#end region

}
