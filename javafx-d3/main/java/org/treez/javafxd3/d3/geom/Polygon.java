package org.treez.javafxd3.d3.geom;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 */
public class Polygon extends Array<Array<Double>> {

	
	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Polygon(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);		
	}

	//#end region

	//#region METHODS

	/**
	 * Returns the signed area of this polygon.
	 * <p>
	 * If the vertices are in counterclockwise order, the area is positive,
	 * otherwise it is negative.
	 * 
	 * @return the signed area
	 */
	public double area() {		
		Double result = callForDouble("area");
		return result;		
	}

	/**
	 * Returns a two-element array representing the centroid of this polygon.
	 * <p>
	 * 
	 * @return a two-element array representing the centroid of this polygon.
	 */
	public Array<?> centroid() {
		JsObject result = call("centroid");
		return new Array<Object>(engine, result);		
	}

	/**
	 * Returns a two-element array representing the centroid of this polygon.
	 * <p>
	 * 
	 * @param k
	 *            a scale factor
	 * @return a two-element array representing the centroid of this polygon.
	 */
	public Array<Double> centroid(double k) {
		JsObject result = call("centroid", k);
		return new Array<Double>(engine, result);			
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
		String arrayString = ArrayUtils.createArrayString(subject);
		String command = "this.clip(" + arrayString + ")";
		JsObject result = evalForJsObject(command);
		return new Polygon(engine, result);		
	}

	public void addPoint(double[] pointCoordinates) {
		String arrayString = ArrayUtils.createArrayString(pointCoordinates);
		String command = "this.push("+arrayString+")";
		eval(command);		
	}

}
