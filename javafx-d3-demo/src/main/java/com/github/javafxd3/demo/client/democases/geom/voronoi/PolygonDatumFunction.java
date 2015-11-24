package com.github.javafxd3.demo.client.democases.geom.voronoi;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class PolygonDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public PolygonDatumFunction(WebEngine webEngine){
		this.webEngine = webEngine;		
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public String apply(final Object context, final Object datum, final int index) {
		
		JSObject jsObject = (JSObject) datum;
		Value value = new Value(webEngine, jsObject);
		
		String polygon = VoronoiTessellationDemo.polygon(webEngine, value);
		return polygon;
	}
		
	
	//#end region
	
	
}
