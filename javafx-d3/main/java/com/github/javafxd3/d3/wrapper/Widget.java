package com.github.javafxd3.d3.wrapper;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Widget extends JavaScriptObject {
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Widget(WebEngine webEngine, JSObject wrappedJsObject){
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region ACCESSORS

	/**
	 * @return
	 */
	public Element getElement() {
		JSObject result = call("getElement");
		return new Element(webEngine, result);
	}
	
	//#end region

}
