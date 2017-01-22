package org.treez.javafxd3.plotly.layout;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Axis extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Axis(JsEngine engine, JsObject jsObject) {
		super(engine,jsObject);		
	}
	
	public Axis(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();	
	}

	//#end region

	//#region ACCESSORS
	
	public void setRange(double min, double max){
		String command = "this.range = ["+min+","+max+"]";
		eval(command);
	}

	public void setShowTickLabels(boolean showTickLabels) {
		setMember("showticklabels", showTickLabels);		
	}
	
	public void setTicks(String ticks) {
		setMember("ticks", ticks);		
	}

	//#end region

}
