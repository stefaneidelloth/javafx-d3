package org.treez.javafxd3.d3.democases.svg.line;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class DefinedDataFunction implements DataFunction<Boolean> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DefinedDataFunction(JsEngine engine){
		this.engine = engine;
	}
	
	//#end region
	
	//#region METHODS	
	
	@Override
	public Boolean apply(Object context, Object datum, int index) {
				
		CustomCoords coords = ConversionUtil.convertObjectTo(datum,  CustomCoords.class, engine);
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