package org.treez.javafxd3.d3.functions.data.attribute;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class AttributeStringDataFunction implements DataFunction<String> {
		
	
	//#region ATTRIBTES
	
	private JsEngine engine;
	
	private String attributeName;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AttributeStringDataFunction(JsEngine engine,String attributeName){	
		this.engine = engine;
		this.attributeName=attributeName;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		Object key = jsObject.eval("this." + attributeName);			
		return key.toString();
	}
	
	//#end region

}
