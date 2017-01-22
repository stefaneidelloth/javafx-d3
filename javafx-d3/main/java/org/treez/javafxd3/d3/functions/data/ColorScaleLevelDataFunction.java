package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.QuantitativeScale;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class ColorScaleLevelDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private QuantitativeScale<?> scale;	
	
	//#end region
	
	//#region CONSTRUCTORS

	public ColorScaleLevelDataFunction(JsEngine engine, QuantitativeScale<?> scale){
		this.engine = engine;
		this.scale = scale;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {			
		JsObject jsObject = (JsObject) engine.toJsObjectIfNotSimpleType(datum);
		Object levelObject = jsObject.eval("this.level");		
		String scaledValue = scale.applyForString(levelObject.toString());		
		return scaledValue;			
	}
	
	//#end region

}
