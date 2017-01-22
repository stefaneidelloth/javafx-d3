package org.treez.javafxd3.plotly.data;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Font extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public Font(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Font(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();	
	}	

	//#end region

	//#region ACCESSORS		
	
	public void setColor(String color) {
		setMember("color", color);		
	}
	
	public void setFamily(String fontFamily) {
		setMember("family", fontFamily);		
	}
	
	public void setSize(int size) {
		setMember("size", size);		
	}	

	//#end region

}
