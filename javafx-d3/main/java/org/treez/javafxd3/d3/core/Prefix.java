package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A <a href="http://en.wikipedia.org/wiki/Metric_prefix">SI Prefix</a>, as
 * returned by D3#formatPrefix()
 * <p>
 * Example:
 * 
 * {@code
 * var prefix = d3.formatPrefix(1.21e9);
 * 
 * console.log(prefix.symbol);
 * 
 * console.log(prefix.scale(1.21e9)); // 1.21
 * }
 * <p>
 */
public class Prefix extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Prefix(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);

	}

	//#end region

	//#region METHODS

	/**
	 * Returns the prefix symbol, such as "M" for millions.
	 * <p>
	 * 
	 * @return the prefix symbol
	 */
	public String symbol() {
		String result = getMemberForString("symbol");
		return result;
	}

	/**
	 * Convert the number to the appropriate prefixed scale.
	 * <p>
	 * 
	 * @param input
	 *            the number to convert
	 * @return the converted number
	 */
	public double scale(double input) {
		Double result = callForDouble("scale", input);
		return result;
	}

	//#end region
}
