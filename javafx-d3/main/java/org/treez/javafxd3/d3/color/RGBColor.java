package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A color defined by red, green and blue components.
 * 
 * @see <a href="https://github.com/mbostock/d3/wiki/Colors">official API</a>
 */
public class RGBColor extends Color {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public RGBColor(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @return the red component in the range [0;255]
	 */
	public int r() {
		Integer result = getMemberForInteger("r");
		return result;
	}

	/**
	 * @return the green component in the range [0;255]
	 */
	public int g() {
		Integer result = getMemberForInteger("g");
		return result;
	}

	/**
	 * @return the blue component in the range [0;255]
	 */
	public int b() {
		Integer result = getMemberForInteger("b");
		return result;
	}

	/**
	 * Returns the equivalent color in HSL space; see d3.hsl for details on the
	 * returned object. The conversion from HSL to RGB is described in CSS3
	 * Color Module Level 3; this is the equivalent reverse operation.
	 * 
	 * @return
	 */
	public HSLColor hsl() {
		JsObject result = call("hsl");
		return new HSLColor(engine, result);
	}

	/**
	 * Returns a brighter copy of this color with a gamma of 1.
	 * 
	 * @return the new copy
	 */
	public RGBColor brighter() {
		JsObject result = call("brighter");
		return new RGBColor(engine, result);
	}

	/**
	 * Returns a brighter copy of this color.
	 * <p>
	 * Each channel is multiplied by 0.7 ^ -k.
	 * 
	 * @param k
	 *            the gamma value
	 * @return the new copy
	 */
	public RGBColor brighter(double k) {
		JsObject result = call("brighter", k);
		return new RGBColor(engine, result);
	}

	/**
	 * Returns a darker copy of this color with a gamma of 1.
	 * 
	 * @return the new copy
	 */
	public RGBColor darker() {
		JsObject result = call("darker");
		return new RGBColor(engine, result);
	}

	/**
	 * Returns a darker copy of this color.
	 * <p>
	 * Each channel is multiplied by 0.7 ^ k.
	 * 
	 * @param k
	 *            the gamma value
	 * @return the new copy
	 */
	public RGBColor darker(double k) {
		JsObject result = call("darker", k);
		return new RGBColor(engine, result);
	}
	
	@Override
	public String toString(){
		return "r: " + r() + ", g: "+ g()+ ", b: " + b();
	}

	//#end region

}
