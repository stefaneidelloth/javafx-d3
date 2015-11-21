package com.github.javafxd3.api.selection.datumfunction;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;

/**
 * A datum function that returns the datum as string
 * 
 */
public class StringDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public StringDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		Value value = Value.create(webEngine,  datum);
		return value.asString();
	}
	
	//#end region

}
