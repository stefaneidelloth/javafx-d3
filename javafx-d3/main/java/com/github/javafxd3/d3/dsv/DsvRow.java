package com.github.javafxd3.d3.dsv;

import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.wrapper.Inspector;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Each row of a DSV file is represented by a {@link DsvRow}.
 * 
 * 
 * 
 */
public class DsvRow extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public DsvRow(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Generic method to get the value of a named field.
	 * 
	 * @param field
	 * @return
	 */
	public Value get(String field) {
		
		//Inspector.inspect(this);
		
		String command = "this['" + field + "']";
		Object resultObj = eval(command);		
		Value entryValue =  Value.create(webEngine, resultObj);
		return entryValue;
	}
}
