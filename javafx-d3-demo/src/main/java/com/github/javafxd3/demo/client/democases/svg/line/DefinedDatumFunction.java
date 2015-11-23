package com.github.javafxd3.demo.client.democases.svg.line;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Inspector;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DefinedDatumFunction implements DatumFunction<Boolean> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DefinedDatumFunction(WebEngine webEngine){
		this.webEngine = webEngine;
	}
	
	//#end region
	
	//#region METHODS	
	
	@Override
	public Boolean apply(Object context, Object d, int index) {
		
		JSObject datum = (JSObject) d;
		//Inspector.inspect(datum);
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