package com.github.javafxd3.api.selection.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;

/**
 * A datum function that returns the datum as string
 * 
 */
public class PrefixPlusIndexDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	private String prefix;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public PrefixPlusIndexDatumFunction(WebEngine webEngine, String prefix){
		this.webEngine=webEngine;
		this.prefix=prefix;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		//Value value = Value.create(webEngine,  datum);
		return prefix + index;
	}
	
	//#end region

}
