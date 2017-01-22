package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class HullTransformDataFunction implements DataFunction<String> {

	//#region ATTRIBUTES

	private JsEngine engine;

	//#end region

	//#region CONSTRUCTORS

	public HullTransformDataFunction(JsEngine engine) {
		this.engine = engine;
	}

	//#end region

	//#region METHODS

	@Override
	public String apply(final Object context, final Object d, final int index) {

		JsObject datum = (JsObject) engine.toJsObjectIfNotSimpleType(d);

		Value value = new Value(engine, datum);

		Object jsCoords = value.as();

		boolean isJsObject = jsCoords instanceof JsObject;
		if (isJsObject) {
			JsObject coordObj = (JsObject) jsCoords;
			HullCoords coords = new HullCoords(engine, coordObj);
			String result = "translate(" + coords.toString() + ")";
			return result;
		} else {
			
			return "translate(0,0)";
		}

	}

	//#end region

}
