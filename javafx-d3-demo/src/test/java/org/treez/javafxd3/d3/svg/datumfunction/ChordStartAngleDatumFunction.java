package org.treez.javafxd3.d3.svg.datumfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.svg.ChordDef;

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
