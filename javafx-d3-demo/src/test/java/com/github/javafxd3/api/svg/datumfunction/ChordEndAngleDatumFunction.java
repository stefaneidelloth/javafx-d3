package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.ChordDef;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns chord end
 *  
 */
public class ChordEndAngleDatumFunction implements DatumFunction<Double> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ChordEndAngleDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);	
		Double end = value.<ChordDef> as().end;
		System.out.println("end: " + end);
		return end;
	}
	
	//#end region

}
