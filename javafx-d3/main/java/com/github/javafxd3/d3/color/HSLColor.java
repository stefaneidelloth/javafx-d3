package com.github.javafxd3.d3.color;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Represents a HSL color
 */
public class HSLColor extends Color {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public HSLColor(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @return the hue component in the range [0;360]
	 */
	public double h() {
		Double result = getMemberForDouble("h");
		return result;
	}

	/**
	 * @return the saturation component in the range [0;1]
	 */
	public double s() {
		Double result = getMemberForDouble("s");
		return result;
	}

	/**
	 * @return the lightness component in the range [0;1]
	 */
	public double l() {
		Double result = getMemberForDouble("l");
		return result;
	}

	/**
	 * Returns the equivalent color in RGB space; see d3.rgb for details on the
	 * returned object. The conversion from HSL to RGB is described in CSS3
	 * Color Module Level 3.
	 * 
	 * @return
	 */
	public RGBColor rgb() {
		JSObject result = call("rgb");
		return new RGBColor(webEngine, result);
	}

	/**
	 * Returns a brighter copy of this color with a gamma of 1.
	 * 
	 * @return the new copy
	 */
	public HSLColor brighter() {
		JSObject result = call("brighter");
		return new HSLColor(webEngine, result);
	}

	/**
	 * Returns a brighter copy of this color.
	 * <p>
	 * The lightness channel is multiplied by 0.7 ^ -k.
	 * 
	 * @param k
	 *            the gamma value
	 * @return the new copy
	 */
	public HSLColor brighter(double k) {
		JSObject result = call("brighter", k);
		return new HSLColor(webEngine, result);
	}

	/**
	 * Returns a darker copy of this color yith a gamme of 1.
	 * 
	 * @return the new copy
	 */
	public HSLColor darker() {
		JSObject result = call("darker");
		return new HSLColor(webEngine, result);
	}

	/**
	 * Returns a darker copy of this color.
	 * <p>
	 * The lightness channel is multiplied by 0.7 ^ k.
	 * 
	 * @param k
	 *            the gamma value
	 * @return the new copy
	 */
	public HSLColor darker(double k) {
		JSObject result = call("darker", k);
		return new HSLColor(webEngine, result);
	}

	//#end region

}
