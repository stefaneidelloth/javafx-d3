package com.github.javafxd3.d3.coords;

import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A DatumFunction that returns the x coordinate
 */
public class XAccessorDatumFunction implements DatumFunction<Double> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	//#end region

	//#region CONSTRUCTORS

	public XAccessorDatumFunction(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	//#end region

	//#region METHODS	

	@Override
	public Double apply(Object context, Object d, int index) {

		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);
		Double result = value.asCoords().x();
		return result;
	}

	//#end region

}
