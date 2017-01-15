package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Each row of a CSV or TSV file is represented by a DsvRow 
 */
public class DsvRow extends JavaScriptObject {

	//#region CONSTRUCTORS

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
		
		String command = "this['" + field + "']";
		Object resultObj = eval(command);		
		Value entryValue =  Value.create(webEngine, resultObj);
		return entryValue;
	}

	
	//#end region
}
