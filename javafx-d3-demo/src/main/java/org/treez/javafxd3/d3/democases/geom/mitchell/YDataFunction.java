package org.treez.javafxd3.d3.democases.geom.mitchell;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class YDataFunction implements DataFunction<Double>{
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YDataFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS
	
	
	@Override
	public Double apply(final Object context, final Object d, final int index) {

		JSObject datum = (JSObject) d;
		Value value = new Value(webEngine, datum);
		Circle circle = value.<Circle> as(Circle.class);
		if (circle==null){
			return null;
		}
		Double result = circle.y;
		return result;
	}
	
	//#end region
	
	

}
