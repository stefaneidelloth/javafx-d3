package org.treez.javafxd3.d3.ease;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * JSNI easing functions.
 */
public class JavascriptEasingFunction extends JavaScriptObject implements EasingFunction {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public JavascriptEasingFunction(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
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
