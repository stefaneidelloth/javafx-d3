package org.treez.javafxd3.d3.geo;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

//TODO: conic projection subclass with parallels() accessors
/**
 * @param <P>
 */
public class Projection<P extends Projection<?>> extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Projection(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public P rotate(double longitude, double latitude) {
		JsObject result = call("rotate", longitude, latitude);
		return (P) new Projection<P>(engine, result);
	}

	/**
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public P center(double longitude, double latitude) {
		JsObject result = call("center", longitude, latitude);
		return (P) new Projection<P>(engine, result);
	}

	/**
	 * Sets the projection’s scale factor to the specified value and returns
	 * the projection.
	 * 
	 * @param factor
	 *            the scale
	 * @return the projection
	 */
	@SuppressWarnings("unchecked")
	public P scale(double factor) {
		JsObject result = call("scale", factor);
		return (P) new Projection<P>(engine, result);
	}

	//#end region
}
