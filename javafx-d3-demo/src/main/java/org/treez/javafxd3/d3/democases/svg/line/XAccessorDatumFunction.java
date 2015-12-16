package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

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

		Double x = value.<CustomCoords> as(CustomCoords.class).x();
		return x;

	}

	public Double apply(String context, String d, int index) {
		return null;
	}

	//#end region
}