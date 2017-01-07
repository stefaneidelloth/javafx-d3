package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Node extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public Node(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS
	
	public void appendChild(Node child) {		
		call("appendChild", child.getJsObject());	
	}

	public <T> T cast(Class<T> targetClass) {
		return ConversionUtil.convertObjectTo(getJsObject(), targetClass, webEngine);	
	}

	public Array<Node> children() {
		String command = "this.children";
		JSObject result = evalForJsObject(command);
		if(result==null){
			return null;
		}
		return new Array<>(webEngine, result);		
	}
	
	//#end region

}
