package com.github.javafxd3.demo.client.democases.geom.mitchell;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class XDatumFunction implements DatumFunction<Double>{
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XDatumFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS
	
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {

		JSObject datum = (JSObject) d;
		//Inspector.inspect(datum);
		
		Value value = new Value(webEngine, datum);
		Circle circle = value.<Circle> as(Circle.class);
		if (circle==null){
			return null;
		}
		
		Double result = circle.x;
		return result;
	}
	
	//#end region
	
	

}
