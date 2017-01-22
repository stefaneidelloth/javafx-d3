package org.treez.javafxd3.plotly.layout.margin;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Margin extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Margin(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Margin(JsEngine engine) {
		super(engine);
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
