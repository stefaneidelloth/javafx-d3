package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Provides factory methods to create colors.
 * <p>
 */
public class Colors extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 */
	public Colors(JsEngine engine) {
		super(engine);
		D3 d3 = new D3(engine);
		JsObject d3Obj = d3.getJsObject();
		setJsObject(d3Obj);
	}

	//#end region

	//#region METHODS

	/**
	 * Constructs a new RGB color with the specified r, g and b channel values.
	 * Each channel must be specified as an integer in the range [0,255]. The
	 * channels are available as the r, g and b attributes of the returned
	 * object.
	 * 
	 * @param r
	 *            the red channel
	 * @param g
	 *            the green channel
	 * @param b
	 *            the blue channel
	 * @return the new color instance
	 */
	public RGBColor rgb(int r, int g, int b) {
		JsObject result = call("rgb", r, g, b);
		if(result==null){
			return null;
		}
		return new RGBColor(engine, result);
	};

	/**
	 * Constructs a new RGB color by parsing the specified color string. The
	 * color string may be in a variety of formats:
	 * <ul>
	 * <li>rgb decimal - "rgb(255,255,255)"
	 * <li>hsl decimal - "hsl(120,50%,20%)"
	 * <li>rgb hexadecimal - "#ffeeaa"
	 * <li>rgb shorthand hexadecimal - "#fea"
	 * <li>named - "red", "white", "blue"
	 * </ul>
	 * 
	 * @param color
	 *            the color string representation
	 * @return the new color
	 */
	public RGBColor rgb(final String color) {
		JsObject result = call("rgb", color);
		if(result==null){
			return null;
		}
		return new RGBColor(engine, result);
	}

	/**
	 * Construct a new RGB color from the existing color object.
	 * <p>
	 * 
	 * @param color
	 *            the existing color object
	 * @return the new color
	 */
	public RGBColor rgb(final Color color) {
		JsObject jsObject = color.getJsObject();
		JsObject result = call("rgb", jsObject);
		if(result==null){
			return null;
		}
		return new RGBColor(engine, result);
	}

	/**
	 * Constructs a new HSL color with the specified hue h, saturation s and
	 * lightness l. The hue must be a number in the range [0,360]. The
	 * saturation and lightness must be in the range 0,1. These values are
	 * available as the h, s and l attributes of the returned object.
	 * 
	 * @param h
	 *            the hue channel [0;360]
	 * @param s
	 *            the saturation channel [0;1]
	 * @param l
	 *            the light channel [0;1]
	 * @return the new color instance
	 */
	public HSLColor hsl(int h, double s, double l) {
		JsObject result = call("hsl", h, s, l);
		if(result==null){
			return null;
		}
		return new HSLColor(engine, result);
	}

	/**
	 * Constructs a new HSL color by parsing the specified color string. The
	 * color string may be in a variety of formats:
	 * <ul>
	 * <li>rgb decimal - "rgb(255,255,255)"
	 * <li>hsl decimal - "hsl(120,50%,20%)"
	 * <li>rgb hexadecimal - "#ffeeaa"
	 * <li>rgb shorthand hexadecimal - "#fea"
	 * <li>named - "red", "white", "blue"
	 * </ul>
	 * 
	 * @param color
	 *            the color string representation
	 * @return the new color
	 */
	public HSLColor hsl(final String color) {
		JsObject result = call("hsl", color);
		if(result==null){
			return null;
		}
		return new HSLColor(engine, result);
	}

	/**
	 * Constructs a new HSL color from an existing color object.
	 * <p>
	 * 
	 * @param color
	 *            the existing color object
	 * @return the new color
	 */
	public HSLColor hsl(final RGBColor color) {
		JsObject jsObject = color.getJsObject();
		JsObject result = call("hsl", jsObject);
		if(result==null){
			return null;
		}
		return new HSLColor(engine, result);
	}

	//#end region
}
