package org.treez.javafxd3.d3.behaviour;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * The behaviour module.
 */
public class Behavior extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Behavior(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Create a new {@link Drag} behavior, that you will configure and apply to
	 * a {@link Selection}.
	 * 
	 * @return the drag behaviour
	 */
	public Drag drag() {
		JSObject result = call("drag");
		return new Drag(webEngine, result);
	}

	/**
	 * Create a new {@link Zoom} behavior, that you will configure and apply to
	 * a {@link Selection}.
	 * 
	 * @return the zoom behaviour
	 */
	public Zoom zoom() {
		JSObject result = call("zoom");
		return new Zoom(webEngine, result);
	}

	//#end region

}
