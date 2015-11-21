package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.ChordDef;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the chord start
 *  
 */
public class ChordStartAngleDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ChordStartAngleDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);	
		Double start =  value.<ChordDef> as().start;
		System.out.println("start: " + start);
		return start;
	}
	
	//#end region

}
