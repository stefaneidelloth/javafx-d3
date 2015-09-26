package com.github.javafxd3.api.wrapper;

import javafx.scene.web.WebEngine;

/**
 * Represents a list of nodes
 * 
 *
 * @param <T>
 */
public class NodeList<T> extends JavaScriptObject {
	
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param webEngine
	 */
	public NodeList(WebEngine webEngine){
		super(webEngine);
	}
	
	//#end region

}
