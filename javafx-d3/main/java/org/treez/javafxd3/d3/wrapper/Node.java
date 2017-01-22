package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Node extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	public Node(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS
	
	public void appendChild(Node child) {		
		call("appendChild", child.getJsObject());	
	}

	public <T> T cast(Class<T> targetClass) {
		return ConversionUtil.convertObjectTo(getJsObject(), targetClass, engine);	
	}

	public Array<Node> children() {
		String command = "this.children";
		JsObject result = evalForJsObject(command);
		if(result==null){
			return null;
		}
		return new Array<>(engine, result);		
	}
	
	//#end region

}
