package org.treez.javafxd3.d3.selection.keyfunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.KeyFunction;

public class IntegerKeyFunction implements KeyFunction<Integer> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public IntegerKeyFunction(JsEngine engine){
		this.engine=engine;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
		
		Integer value = ConversionUtil.convertObjectTo(datum, Integer.class, engine);
		return value;
	}	
	
	//#end region

}
