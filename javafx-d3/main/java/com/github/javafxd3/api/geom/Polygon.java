package com.github.javafxd3.api.geom;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 */
public class Polygon extends ArrayList<List<Double>> {

	// #region ATTRIBUTES

	/**
	 * Serialization id
	 */
	private static final long serialVersionUID = -5064777499799164675L;

	WebEngine webEngine;

	JSObject wrappedJsObject;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Polygon(WebEngine webEngine, JSObject wrappedJsObject) {
		super();
		this.webEngine = webEngine;
		this.wrappedJsObject = wrappedJsObject;
	}

	// #end region

	// #region METHODS

	/**
	 * Returns the signed area of this polygon.
	 * <p>
	 * If the vertices are in counterclockwise order, the area is positive,
	 * otherwise it is negative.
	 * 
	 * @return the signed area
	 */
	public double area() {
		throw new IllegalStateException("not yet implemented");
		//Double result = callForDouble("area");
		//return result;
		
	}

	/**
	 * Returns a two-element array representing the centroid of this polygon.
	 * <p>
	 * 
	 * @return a two-element array representing the centroid of this polygon.
	 */
	public List<?> centroid() {
		throw new IllegalStateException("not yet implemented");
		//return this.centroid();
	}

	/**
	 * Returns a two-element array representing the centroid of this polygon.
	 * <p>
	 * 
	 * @param k
	 *            a scale factor
	 * @return a two-element array representing the centroid of this polygon.
	 */
	public List<Double> centroid(double k) {
		throw new IllegalStateException("not yet implemented");
		//return this.centroid(k);
	}

	/**
	 * Clips the subject polygon against this polygon.
	 * <p>
	 * In other words, returns a polygon representing the intersection of this
	 * polygon and the subject polygon.
	 * <p>
	 * Assumes the clip polygon is counterclockwise and convex.
	 * <p>
	 * 
	 * @param subject
	 *            the polygon to clip against
	 * @return the clip polygon
	 */
	public Polygon clip(Double[][] subject) {
		throw new IllegalStateException("not yet implemented");
		//return this.clip(subject);
	}

}
