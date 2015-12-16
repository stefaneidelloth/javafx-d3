package org.treez.javafxd3.d3.selection.datumfunction;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A datum function that returns the datum as object array
 * 
 */
public class ObjectArrayDatumFunction implements DatumFunction<Object[]> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ObjectArrayDatumFunction(WebEngine webEngine){
		this.webEngine=webEngine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Object[] apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		
		System.out.println(context + " " + value.asString() + " " + index);
		Object[] as = value.as();
		System.out.println(as);
		return as;
		
	}
	
	//#end region

}
