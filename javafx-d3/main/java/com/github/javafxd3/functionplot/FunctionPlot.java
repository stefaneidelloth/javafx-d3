package com.github.javafxd3.functionplot;

import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class FunctionPlot extends JavaScriptObject {
	
	//#region ATTRIBUTES
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public FunctionPlot(WebEngine webEngine){
		super(webEngine);
		JSObject functionPlot = (JSObject) webEngine.executeScript("functionPlot");
		setJsObject(functionPlot);
	}
	
	//#end region
	
	//#region METHODS
	
	public void apply(Options options){
		JSObject jsOptions = options.getJsObject();
		callThis(jsOptions);
	}
	
	//#end region

}
