package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.arrays.Array;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 */
public class Node extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Node(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS

	/**
	 * @param cloneNode
	 */
	public void appendChild(Node cloneNode) {
		throw new IllegalStateException("not yet implemented");
		
	}

	public <T> T cast() {
		throw new IllegalStateException("not yet implemented");
		//return null;
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
