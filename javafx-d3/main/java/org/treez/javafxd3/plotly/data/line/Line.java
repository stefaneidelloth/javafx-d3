package org.treez.javafxd3.plotly.data.line;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Line extends JavaScriptObject {	

	//#region CONSTRUCTORS

	public Line(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Line(WebEngine webEngine) {
		super(webEngine);
		setEmptyObjectAsJsObject();	
	}

	//#end region

	//#region METHODS	
	
	public void setColor(String color){
		setMember("color", color);
	}
	
	public void setWidth(double widthInPx){
		setMember("width", widthInPx);
	}
	
	public void setDash(Dash dash){
		setMember("dash", dash.toString());
	}
	
	public void setDash(double dashLengthInPx){
		setMember("dash", dashLengthInPx);
	}
	
	public void setShape(LineShape lineShape){
		setMember("shape", lineShape.toString());
	}
	
	/**
	 * Sets the amount of smoothing for the contour lines, where "0" corresponds to no smoothing. 
	 * Max value: 1.3
	 */
	public void setSmoothing(double smoothing){
		setMember("smoothing", smoothing);
	}
	
	

	//#end region

}
