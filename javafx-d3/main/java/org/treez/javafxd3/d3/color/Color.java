package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Constructing visualizations often involves working with colors.
 * <p>
 * Even though your browser understands a lot about colors, it doesn't offer
 * much help in manipulating colors through JavaScript.
 * <p>
 * So D3 provides representations for both RGB and HSL colors, allowing
 * interpolation in both color spaces, and making colors brighter or darker. For
 * more about color manipulation, see the Wikipedia entries on RGB and HSL.
 * <p>
 * Note: while you can work with colors directly, you might also want to take a
 * look at D3's built-in {@link D3#interpolateRgb}, {@link D3#interpolateHsl}
 * and {@link D3#scale}.
 * <p>
 * If you are looking for color palettes, see the ordinal scales reference.
 * <p>
 */
public class Color extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappingJsObject
	 */
	public Color(WebEngine webEngine, JSObject wrappingJsObject) {
		super(webEngine);
		setJsObject(wrappingJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Converts to a RGB hexadecimal string, such as "#f7eaba".
	 * 
	 * @return hexa representation of the color
	 */
	public String toHexaString() {
		String result = callForString("toString");
		return result;
	}

	//#end region

}
