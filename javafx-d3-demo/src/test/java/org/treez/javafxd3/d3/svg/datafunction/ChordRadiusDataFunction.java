package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.ChordDef;

public class ChordRadiusDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public ChordRadiusDataFunction(JsEngine engine){
		this.engine=engine;
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		ChordDef chordDef = ConversionUtil.convertObjectTo(datum, ChordDef.class, engine);		
		Double radius = chordDef.radius;
		System.out.println("radius: " + radius);
		return radius;
	}
	
	//#end region

}
