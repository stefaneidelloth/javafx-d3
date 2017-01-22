package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Each row of a CSV or TSV file is represented by a DsvRow 
 */
public class DsvRow extends JavaScriptObject {

	//#region CONSTRUCTORS

	public DsvRow(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
		Value entryValue =  Value.create(engine, resultObj);
		return entryValue;
	}

	
	//#end region
}
