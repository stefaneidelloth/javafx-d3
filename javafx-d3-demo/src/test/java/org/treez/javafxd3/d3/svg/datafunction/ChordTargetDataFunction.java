package org.treez.javafxd3.d3.svg.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.ChordDef;


public class ChordTargetDataFunction implements DataFunction<ChordDef> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param engine
	 */
	public ChordTargetDataFunction(){			
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public ChordDef apply(Object context, Object d, int index) {
		System.out.println("target: " + d);
		return new ChordDef(index * 5, index * 5, index * 5);
	}
	
	//#end region

}
