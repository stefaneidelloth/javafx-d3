package org.treez.javafxd3.d3.functions;

import netscape.javascript.JSObject;

/**
 * A DatumFunction that delegates the apply call to a JSObject
 */
public class DelegatingDatumFunction implements DatumFunction<Object> {
	
	//#region ATTRIBUTES
	
	JSObject wrappedJsObject;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public DelegatingDatumFunction(JSObject wrappedJsObject){
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
