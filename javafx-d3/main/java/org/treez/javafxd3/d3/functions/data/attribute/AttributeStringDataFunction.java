package org.treez.javafxd3.d3.functions.data.attribute;

import org.treez.javafxd3.d3.functions.DataFunction;

import netscape.javascript.JSObject;

public class AttributeStringDataFunction implements DataFunction<String> {
		
	
	//#region ATTRIBTES
	
	String attributeName;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AttributeStringDataFunction(String attributeName){	
		this.attributeName=attributeName;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		Object key = jsObject.eval("this.datum." + attributeName);			
		return key.toString();
	}
	
	//#end region

}
