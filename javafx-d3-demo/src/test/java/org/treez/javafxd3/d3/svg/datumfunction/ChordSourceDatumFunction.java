package org.treez.javafxd3.d3.svg.datumfunction;

import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.svg.ChordDef;

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
