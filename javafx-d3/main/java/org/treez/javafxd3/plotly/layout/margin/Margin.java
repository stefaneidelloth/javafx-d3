package org.treez.javafxd3.plotly.layout.margin;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Margin extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Margin(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Margin(WebEngine webEngine) {
		super(webEngine);
		setEmptyObjectAsJsObject();	
	}

	//#end region

	//#region ACCESSORS	
	
	public void setL(double l){
		setMember("l", l);
	}
	
	public void setR(double r){
		setMember("r", r);
	}
	
	public void setT(double t){
		setMember("t", t);
	}
	
	public void setB(double b){
		setMember("b", b);
	}

	//#end region

}
