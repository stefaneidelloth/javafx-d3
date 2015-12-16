package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.democases.geom.hull.HullDemo.MyCoords;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullTransformDatumFunction implements DatumFunction<String> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	//#end region

	//#region CONSTRUCTORS

	public HullTransformDatumFunction(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	//#end region

	//#region METHODS

	@Override
	public String apply(final Object context, final Object d, final int index) {

		JSObject datum = (JSObject) d;

		Value value = new Value(webEngine, datum);

		Object jsCoords = value.as();

		boolean isJsObject = jsCoords instanceof JSObject;
		if (isJsObject) {
			JSObject coordObj = (JSObject) jsCoords;
			MyCoords coords = new MyCoords(webEngine, coordObj);
			String result = "translate(" + coords.toString() + ")";
			return result;
		} else {
			
			return "translate(0,0)";
		}

	}

	//#end region

}
