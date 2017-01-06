package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.ChordDef;

/**
 * A datum function that creates a source chord definition 
 *  
 */
public class ChordSourceDataFunction implements DataFunction<ChordDef> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ChordSourceDataFunction(){			
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
