package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;

import netscape.javascript.JSObject;

/**
 * A DataFunction that delegates the apply call to a JSObject
 */
public class DelegatingDataFunction implements DataFunction<Object> {
	
	//#region ATTRIBUTES
	
	JSObject wrappedJsObject;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public DelegatingDataFunction(JSObject wrappedJsObject){
		this.wrappedJsObject = wrappedJsObject;
	}
	//#end region
	
	//#region METHODS

	@Override
	public Object apply(Object context, Object datum, int index) {		
		Object applyResult = wrappedJsObject.call("apply", datum, index);
		return applyResult;	
	}
	
	//#end region

}
