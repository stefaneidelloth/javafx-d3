package com.github.javafxd3.api.svg.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.svg.ChordDef;

/**
 * A datum function that creates a source chord definition 
 *  
 */
public class ChordSourceDatumFunction implements DatumFunction<ChordDef> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ChordSourceDatumFunction(){			
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public ChordDef apply(Object context, Object d, int index) {
		System.out.println("source: " + d);
		return new ChordDef(index * 5, index * 5, index * 5);
	}
	
	//#end region

}
