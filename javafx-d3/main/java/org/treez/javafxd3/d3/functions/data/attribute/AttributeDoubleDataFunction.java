package org.treez.javafxd3.d3.functions.data.attribute;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class AttributeDoubleDataFunction implements DataFunction<Double> {
			
	//#region ATTRIBTES
	
	private JsEngine engine;
	
	private String attributeName;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public AttributeDoubleDataFunction(JsEngine engine, String attributeName){	
		this.engine=engine;
		this.attributeName = attributeName;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);	
		Object object = jsObject.eval("this." + attributeName);			
		Double value = Double.parseDouble(object.toString());
		return value;
	}
	
	//#end region

}
