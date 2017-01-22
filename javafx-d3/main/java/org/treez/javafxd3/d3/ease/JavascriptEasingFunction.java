package org.treez.javafxd3.d3.ease;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * JSNI easing functions.
 */
public class JavascriptEasingFunction extends JavaScriptObject implements EasingFunction {

	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public JavascriptEasingFunction(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);

	}

	//#end region

	//#region METHODS

	
	@Override
	public  double ease(double t){
		String command = "this("+t+")";
		Object resultObj = eval(command);
		String stringValue = resultObj.toString();
		Double result = Double.parseDouble(stringValue);
		return result;
	}
	
	//#end region
}
