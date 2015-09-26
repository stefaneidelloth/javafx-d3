package com.github.javafxd3.api.wrapper;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 */
public class Node extends JavaScriptObject {

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

	public Object children() {
		throw new IllegalStateException("not yet implemented");
		//return null;
	}
	
	

}
