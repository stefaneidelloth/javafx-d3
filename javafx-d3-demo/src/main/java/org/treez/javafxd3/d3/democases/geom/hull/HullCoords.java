package org.treez.javafxd3.d3.democases.geom.hull;

import org.treez.javafxd3.d3.coords.Coords;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class HullCoords extends Coords {

	//#region CONSTRUCTORS

	public HullCoords(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	public HullCoords(WebEngine webEngine, double x, double y) {
		super(webEngine, x, y);
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return x() + "," + y();
	}

	//#end region

}
