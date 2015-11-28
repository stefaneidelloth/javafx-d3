package com.github.javafxd3.d3.wrapper;

import java.util.Iterator;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class WidgetCollection extends JavaScriptObject implements Iterable<Widget> {
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public WidgetCollection(WebEngine webEngine, JSObject wrappedJsObject){
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
	
	//#end region
	
	//#region METHODS
	
	public Iterator<Widget> iterator() {
		throw new IllegalStateException("not yet implemented");
	}
	
	//#end region
	
}
