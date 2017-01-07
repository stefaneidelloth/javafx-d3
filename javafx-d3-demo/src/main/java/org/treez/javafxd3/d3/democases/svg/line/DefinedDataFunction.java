package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DefinedDataFunction implements DataFunction<Boolean> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DefinedDataFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS	
	
	@Override
	public Boolean apply(Object context, Object d, int index) {
		
		JSObject datum = (JSObject) d;		
		Value value = new Value(webEngine, datum);
		
		CustomCoords coords = value.<CustomCoords> as(CustomCoords.class);
		if (coords!=null){
			boolean defined = coords.defined();
			return defined;
		}
			return false;
		
	}
	
	public Boolean apply(String context, String d, int index){
		return null;
	}
	
	//#end region
}