package org.treez.javafxd3.d3.democases.geom.voronoi;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class TransformDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public TransformDataFunction(WebEngine webEngine){
		this.webEngine = webEngine;		
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public String apply(final Object context, final Object d, final int index) {		
		JSObject datum = (JSObject) d;		
		Value value = new Value(webEngine, datum);	
		//Inspector.inspect(datum);
		String transform =  "translate(" + value.asString() + ")";
		return transform;
	}
	
	//#end region
	
	
}
