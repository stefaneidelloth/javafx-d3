package com.github.javafxd3.api.dsv;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Each row of a DSV file is represented by a {@link DsvRow}.
 * 
 * 
 * 
 */
public class DsvRow extends JavaScriptObject {

	// #region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public DsvRow(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	// #end region

	// #region METHODS

	/**
	 * Generic method to get the value of a named field.
	 * 
	 * @param field
	 * @return
	 */
	public Value get(String field) {		
		String command = "this['" + field + "']";
		Object resultObj = eval(command);		
		Value entryValue =  Value.create(webEngine, resultObj);
		return entryValue;
	}
}
