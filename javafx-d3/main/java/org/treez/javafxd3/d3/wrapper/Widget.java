package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Widget extends JavaScriptObject {
	
	//#region CONSTRUCTORS
	
	public Widget(JsEngine engine, JsObject wrappedJsObject){
		super(engine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region ACCESSORS

	public Element getElement() {
		JsObject result = call("getElement");
		return new Element(engine, result);
	}
	
	//#end region

}
