package org.treez.javafxd3.d3.wrapper.canvas;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Context2d extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public Context2d(WebEngine webEngine, JSObject wrappedJsObject){
		super(webEngine, wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS
	
	public void save() {		
		call("save");		
	}
	
	public void translate(double x, double y) {
		call("translate",x,y);		
	}

	public void scale(int x, int y) {
		call("scale", x,y);		
	}
	
	public void rotate(int angleInDegrees) {
		call("rotate", angleInDegrees);		
	}

	public void beginPath() {
		call("beginPath");		
	}

	public void moveTo(double x, double y) {
		call("moveTo", x, y);		
	}

	public void lineTo(double x, double y) {
		call("lineTo",x,y);		
	}

	public void stroke() {
		call("stroke");		
	}

	public void restore() {
		call("restore");		
	}

	public void fillRect(int x, int y, double width, double height) {
		call("fillRect",x,y,width,height);		
	}	
	
	public void setLineWidth(double value) {
		String command = "this.lineWidth ="+value;
		eval(command);		
	}

	public void setFillStyle(String value) {
		String command = "this.fillStyle ='"+ value+"'";
		eval(command);		
	}	

	public void setGlobalCompositeOperation(String value) {
		String command = "this.globalCompositeOperation ='"+ value + "'";
		eval(command);		
	}	

	public void setStrokeStyle(String style) {
		String command = "this.strokeStyle ='" + style + "'";
		eval(command);
	}

	
	
	//#end region
	
	
}
