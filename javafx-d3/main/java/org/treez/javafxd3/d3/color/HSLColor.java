package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Represents a HSL color
 */
public class HSLColor extends Color {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public HSLColor(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
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
		JsObject result = call("rgb");
		return new RGBColor(engine, result);
	}

	/**
	 * Returns a brighter copy of this color with a gamma of 1.
	 * 
	 * @return the new copy
	 */
	public HSLColor brighter() {
		JsObject result = call("brighter");
		return new HSLColor(engine, result);
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
		JsObject result = call("brighter", k);
		return new HSLColor(engine, result);
	}

	/**
	 * Returns a darker copy of this color yith a gamme of 1.
	 * 
	 * @return the new copy
	 */
	public HSLColor darker() {
		JsObject result = call("darker");
		return new HSLColor(engine, result);
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
		JsObject result = call("darker", k);
		return new HSLColor(engine, result);
	}

	//#end region

}
