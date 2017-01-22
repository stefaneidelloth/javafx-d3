package org.treez.javafxd3.d3.wrapper.canvas;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class CanvasElement extends JavaScriptObject {

	//#region CONSTRUCTORS

	public CanvasElement(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region ACCESSORS

	public Context2d getContext2d() {

		String command = "this.getContext('2d')";
		JsObject result = evalForJsObject(command);
		if (result == null) {
			return null;
		}

		return new Context2d(engine, result);

	}

	//#end region
}
