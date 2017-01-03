package org.treez.javafxd3.d3.functions;

import netscape.javascript.JSObject;

public class AttributeStringDatumFunction implements DatumFunction<String> {
		
	
	//#region ATTRIBTES
	
	String attributeName;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AttributeStringDatumFunction(String attributeName){	
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
