package org.treez.javafxd3.plotly.configuration;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Configuration extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Configuration(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Configuration(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();	
	}

	//#end region

	//#region METHODS	

	//#end region

}
