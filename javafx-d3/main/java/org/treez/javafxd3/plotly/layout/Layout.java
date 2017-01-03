package org.treez.javafxd3.plotly.layout;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.plotly.layout.margin.Margin;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Layout extends JavaScriptObject {	

	//#region CONSTRUCTORS

	public Layout(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Layout(WebEngine webEngine) {
		super(webEngine);
		setEmptyObjectAsJsObject();	
	}
	//#end region

	//#region ACCESSORS
	
	public void setWidth(double width) {
		setMember("width", width);		
	}
	
	public void setHeight(double height) {
		setMember("height", height);		
	}

	public void setXAxis(Axis xAxis) {
		setMember("xaxis", xAxis.getJsObject());		
	}	
	
	public void setYAxis(Axis yAxis) {
		setMember("yaxis", yAxis.getJsObject());		
	}	
	
	public void setMargin(Margin margin) {
		setMember("margin", margin.getJsObject());		
	}

	//#end region

}
