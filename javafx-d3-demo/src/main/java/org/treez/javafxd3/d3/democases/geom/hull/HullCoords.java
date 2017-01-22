package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.coords.Coords;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class HullCoords extends Coords {

	//#region CONSTRUCTORS

	public HullCoords(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	public HullCoords(JsEngine engine, double x, double y) {
		super(engine, x, y);
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return x() + "," + y();
	}

	//#end region

}
