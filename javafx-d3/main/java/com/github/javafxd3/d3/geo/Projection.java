package com.github.javafxd3.d3.geo;

import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

//TODO: conic projection subclass with parallels() accessors
/**
 * @param <P>
 */
public class Projection<P extends Projection<?>> extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Projection(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public P rotate(double longitude, double latitude) {
		JSObject result = call("rotate", longitude, latitude);
		return (P) new Projection<P>(webEngine, result);
	}

	/**
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public P center(double longitude, double latitude) {
		JSObject result = call("center", longitude, latitude);
		return (P) new Projection<P>(webEngine, result);
	}

	/**
	 * Sets the projection’s scale factor to the specified value and returns
	 * the projection.
	 * 
	 * @param factor
	 *            the scale
	 * @return the projection
	 */
	public P scale(double factor) {
		JSObject result = call("scale", factor);
		return (P) new Projection<P>(webEngine, result);
	}

	//#end region
}
