package org.treez.javafxd3.plotly.data.line;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Line extends JavaScriptObject {	

	//#region CONSTRUCTORS

	public Line(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Line(JsEngine engine) {
		super(engine);
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
