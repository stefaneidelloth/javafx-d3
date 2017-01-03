package org.treez.javafxd3.plotly.data;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Font extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public Font(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Font(WebEngine webEngine) {
		super(webEngine);
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
