package org.treez.javafxd3.d3.wrapper;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Widget extends JavaScriptObject {
	
	//#region CONSTRUCTORS
	
	public Widget(WebEngine webEngine, JSObject wrappedJsObject){
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region ACCESSORS

	public Element getElement() {
		JSObject result = call("getElement");
		return new Element(webEngine, result);
	}
	
	//#end region

}
