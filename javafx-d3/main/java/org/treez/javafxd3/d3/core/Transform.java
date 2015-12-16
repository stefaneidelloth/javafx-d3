package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A Transform is a representation of a SVG
 * <a href="http://www.w3.org/TR/SVG/coords.html#TransformAttribute">transform
 * attribute</a>.
 * <p>
 * It allows parsing the svg transform attribute with {@link #parse(String)},
 * manipulation using setters, such as {@link #rotate(double)}, then generation
 * of the transform attribute with {@link #toString()}
 * <p>
 */
public class Transform extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public Transform(WebEngine webEngine) {
		super(webEngine);
		JSObject d3 = (JSObject) webEngine.executeScript("d3");
		JSObject transform = (JSObject) d3.getMember("transform");
		setJsObject(transform);
	}

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Transform(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Parses the given 2D affine transform string, as defined by SVG's
	 * <a href="http://www.w3.org/TR/SVG/coords.html#TransformAttribute">
	 * transform attribute</a>.
	 * <p>
	 * The transform is then decomposed to a standard representation of
	 * translate, rotate, x-skew and scale.
	 * <p>
	 * This behavior is standardized by CSS: see
	 * <a href="http:decomposing">matrix decomposition for animation</a>.
	 * <p>
	 * 
	 * @param transformString
	 *            the transform string as in <a href=
	 *            "http://www.w3.org/TR/SVG/coords.html#TransformAttribute" >SVG
	 *            </a>
	 * @return the Transform object
	 */
	public Transform parse(String transformString) {
		String command = "d3.transform('" + transformString + "')";
		JSObject result = evalForJsObject(command);
		return new Transform(webEngine, result);
	}

	/**
	 * Returns the rotation angle θ of this transform, in degrees.
	 * 
	 * @return the rotation angle
	 */
	public double rotate() {
		Double result = getMemberForDouble("rotate");
		return result;
	}

	/**
	 * Set the rotation angle of this transform
	 * 
	 * @param degrees
	 *            the rotate degree
	 * @return the modified transformation
	 */
	public Transform rotate(double degrees) {
		String command = "this.rotate = " + degrees;
		eval(command);
		return this;
	}

	/**
	 * Returns the [dx, dy] translation of this transform, as a two-element
	 * array in local coordinates (typically pixels).
	 * <p>
	 * 
	 * @return translation coords
	 */
	public Array<Double> translate() {
		JSObject result = getMember("translate");
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Create a translation by x and y
	 * 
	 * @param x
	 * @param y
	 * @return the {@link Transform}.
	 */
	public Transform translate(final int x, final int y) {
		String command = "this.translate=[" + x + "," + y + "]";
		eval(command);
		return this;
	}

	/**
	 * Create a translation by x and 0.
	 * 
	 * @param x
	 *            the x
	 * @return the {@link Transform}
	 */
	public Transform translate(final int x) {
		String command = "this.translate=[" + x + ",0]";
		eval(command);
		return this;
	}

	/**
	 * Create a scale operation by x and y
	 * 
	 * @param x
	 * @param y
	 * @return the {@link Transform}.
	 */
	public Transform scale(final int x, final int y) {
		String command = "this.scale=[" + x + "," + y + "]";
		eval(command);
		return this;
	}

	/**
	 * Create a scale operation by x and by y = x.
	 * 
	 * @param x
	 *            the x
	 * @return the {@link Transform}
	 */
	public Transform scale(final int x) {
		String command = "this.scale=[" + x + ",0]";
		eval(command);
		return this;
	}

	/**
	 * Returns the x-skew φ of this transform, in degrees.
	 * <p>
	 * 
	 * @return the x skew
	 */
	public double skew() {
		Double result = getMemberForDouble("skew");
		return result;
	}

	/**
	 * @param x
	 * @return
	 */
	public Transform skew(double x) {
		String command = "this.skew = " + x;
		eval(command);
		return this;
	}

	/**
	 * Returns the [kx, ky] scale of this transform, as a two-element array.
	 * <p>
	 * 
	 * @return translation coords
	 */
	public Array<Double> scale() {
		JSObject result = getMember("scale");
		return new Array<Double>(webEngine, result);
	}

	public String toString() {
		String command = "this.toString()";
		String result = evalForString(command);
		return result;
	}

	//#end region

}
