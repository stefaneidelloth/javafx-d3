package org.treez.javafxd3.d3.democases.geom.voronoi;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;

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
