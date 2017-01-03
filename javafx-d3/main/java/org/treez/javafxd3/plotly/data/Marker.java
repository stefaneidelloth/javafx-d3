package org.treez.javafxd3.plotly.data;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


public class Marker extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Marker(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Marker(WebEngine webEngine) {
		super(webEngine);
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
		JSObject result =  getMember("marker");
		return new Marker(webEngine, result);
	}

	//#end region

}
