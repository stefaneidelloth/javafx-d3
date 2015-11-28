package com.github.javafxd3.functionplot;

import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Options extends JavaScriptObject {
	
	//#region ATTRIBUTES
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public Options(WebEngine webEngine){
		super(webEngine);
				
		String command = "var options = {target: null, " //
				+ "title: null, " //
				+ "xDomain: null, " //
				+ "yDomain: null, " //
				+ "xLabel: null, " //
				+ "yLabel: null, " //
				+ "disableZoom: false, " //
				+ "grid: false, " //
				+ "tip: null, " //
				+ "annotations: null, " //
				+ "data: null, " //
				+ "plugins: null" //
				+ "};";
		webEngine.executeScript(command);
		JSObject options = (JSObject) webEngine.executeScript("options");
		setJsObject(options);
	}
	
	//#end region
	
	//#region METHODS
	
	//#end region
	
	//#region ACCESSORS
	
	//#region TARGET
	
	public String getTarget(){
		String result = getMemberForString("target");
		return result;
	}
	
	public Options setTarget(String target){
		setMember("target", target);
		return this;
	}
	
	//#end region

	//#region TITLE
	
	public String getTitle(){
		String result = getMemberForString("title");
		return result;
	}
	
	public Options setTitle(String title){
		setMember("title", title);
		return this;
	}
	
	//#end region
	
	//#region X LABEL
	
	//#end region
	
	public String getXLabel(){
		String result = getMemberForString("xLabel");
		return result;
	}
	
	public Options setXLabel(String xLabel){
		setMember("xLabel", xLabel);
		return this;
	}
	
	//#end region
	
	//#region Y LABEL
	
	public String getYLabel(){
		String result = getMemberForString("xLabel");
		return result;
	}
	
	public Options setYLabel(String yLabel){
		setMember("yLabel", yLabel);
		return this;
	}
	
	//#end region
	
	//#region ZOOM
			
	public Options enableZoom(){
		setMember("disableZoom", false);
		return this;
	}
	
	public Options disableZoom(){
		setMember("disableZoom", true);
		return this;
	}
	
	public boolean isEnabledZoom(){
		boolean isDisabled = getMemberForBoolean("disableZoom");
		return !isDisabled;
	}
	
	//#end region

	//#region GRID
	
	public Options enableGrid(){
		setMember("grid", true);
		return this;
	}
	
	public Options disableGrid(){
		setMember("grid", false);
		return this;
	}
	
	public boolean isEnabledGrid(){
		boolean isGrid = getMemberForBoolean("grid");
		return isGrid;
	}
	
	//#end region
	
	//#region DATA
	
	public Options setDataExpression(String dataExpression){
		JSObject data = evalForJsObject(dataExpression);
		setMember("data", data);
		return this;
	}
	
	//#end region
	
	
	//#end region

}
