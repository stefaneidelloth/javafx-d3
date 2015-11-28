package com.github.javafxd3.d3.color;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Provides factory methods to create colors.
 * <p>
 */
public class Colors extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 */
	public Colors(WebEngine webEngine) {
		super(webEngine);
		D3 d3 = new D3(webEngine);
		JSObject d3Obj = d3.getJsObject();
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
		JSObject result = call("rgb", r, g, b);
		return new RGBColor(webEngine, result);
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
		JSObject result = call("rgb", color);
		return new RGBColor(webEngine, result);
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
		JSObject jsObject = color.getJsObject();
		JSObject result = call("rgb", jsObject);
		return new RGBColor(webEngine, result);
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
		JSObject result = call("hsl", h, s, l);
		return new HSLColor(webEngine, result);
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
		JSObject result = call("hsl", color);
		return new HSLColor(webEngine, result);
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
		JSObject jsObject = color.getJsObject();
		JSObject result = call("hsl", jsObject);
		return new HSLColor(webEngine, result);
	}

	//#end region
}
