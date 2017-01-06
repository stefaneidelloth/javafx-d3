package org.treez.javafxd3.d3.wrapper.canvas;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class CanvasElement extends JavaScriptObject {

	//#region CONSTRUCTORS

	public CanvasElement(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region ACCESSORS

	public Context2d getContext2d() {

		String command = "this.getContext('2d')";
		JSObject result = evalForJsObject(command);
		if (result == null) {
			return null;
		}

		return new Context2d(webEngine, result);

	}

	//#end region
}
