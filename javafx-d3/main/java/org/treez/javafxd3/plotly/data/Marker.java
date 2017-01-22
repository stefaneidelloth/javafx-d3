package org.treez.javafxd3.plotly.data;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


public class Marker extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Marker(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Marker(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();	
	}

	//#end region

	//#region METHODS	
	
	public void setType(String type){
		setMember("type", type);
	}
	
	public String getType(String type){
		return getMemberForString("type");
	}
	
	public void setMarker(Marker marker){
		setMember("marker", marker.getJsObject());
	}
	
	public Marker getMarker(){
		JsObject result =  getMember("marker");
		return new Marker(engine, result);
	}

	//#end region

}
