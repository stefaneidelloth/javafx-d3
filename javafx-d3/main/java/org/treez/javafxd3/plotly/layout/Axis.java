package org.treez.javafxd3.plotly.layout;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Axis extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Axis(WebEngine webEngine, JSObject jsObject) {
		super(webEngine,jsObject);		
	}
	
	public Axis(WebEngine webEngine) {
		super(webEngine);
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
