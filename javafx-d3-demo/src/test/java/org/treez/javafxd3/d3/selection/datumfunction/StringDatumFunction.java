package org.treez.javafxd3.d3.selection.datumfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

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
		JSObject jsDatum = (JSObject) datum;		
		Value value = new Value(webEngine,  jsDatum);
		String stringValue = value.asString();
		return stringValue;
	}
	
	//#end region

}
