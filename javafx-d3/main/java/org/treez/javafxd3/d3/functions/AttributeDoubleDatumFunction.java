package org.treez.javafxd3.d3.functions;

import netscape.javascript.JSObject;

public class AttributeDoubleDatumFunction implements DatumFunction<Double> {
		
	
	//#region ATTRIBTES
	
	String attributeName;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AttributeDoubleDatumFunction(String attributeName){	
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JSObject jsObject = (JSObject) datum;	
		Object object = jsObject.eval("this.datum." + attributeName);			
		Double value = Double.parseDouble(object.toString());
		return value;
	}
	
	//#end region

}
