package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

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
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Prefix(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
