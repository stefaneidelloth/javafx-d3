package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;


public class PrefixPlusIndexDataFunction implements DataFunction<String> {
	
	//#region ATTRIBUTES	
	
	private String prefix;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public PrefixPlusIndexDataFunction(String prefix){		
		this.prefix=prefix;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public String apply(Object context, Object datum, int index) {		
		return prefix + index;
	}
	
	//#end region

}
