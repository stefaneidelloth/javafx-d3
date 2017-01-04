package org.treez.javafxd3.d3.selection.datumfunction;

import org.treez.javafxd3.d3.functions.DatumFunction;


public class PrefixPlusIndexDatumFunction implements DatumFunction<String> {
	
	//#region ATTRIBUTES	
	
	private String prefix;
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public PrefixPlusIndexDatumFunction(String prefix){		
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
