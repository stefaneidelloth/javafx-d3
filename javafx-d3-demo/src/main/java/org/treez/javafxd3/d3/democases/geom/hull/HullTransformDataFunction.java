package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullTransformDataFunction implements DataFunction<String> {

	//#region ATTRIBUTES

	private WebEngine webEngine;

	//#end region

	//#region CONSTRUCTORS

	public HullTransformDataFunction(WebEngine webEngine) {
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
			HullCoords coords = new HullCoords(webEngine, coordObj);
			String result = "translate(" + coords.toString() + ")";
			return result;
		} else {
			
			return "translate(0,0)";
		}

	}

	//#end region

}
