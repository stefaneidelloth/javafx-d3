package org.treez.javafxd3.d3.behaviour;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * The behaviour module.
 */
public class Behavior extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Behavior(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
		JsObject result = call("drag");
		return new Drag(engine, result);
	}

	/**
	 * Create a new {@link Zoom} behavior, that you will configure and apply to
	 * a {@link Selection}.
	 * 
	 * @return the zoom behaviour
	 */
	public Zoom zoom() {
		JsObject result = call("zoom");
		return new Zoom(engine, result);
	}

	//#end region

}
