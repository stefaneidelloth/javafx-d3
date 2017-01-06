package org.treez.javafxd3.d3.democases.geom.voronoi;

import org.treez.javafxd3.d3.functions.DataFunction;

public class ClassDataFunction implements DataFunction<String> {
	
	
	
	//#region CONSTRUCTORS
	
	public ClassDataFunction(){		
	}
	
	//#end region
	
	//#region METHODS
	
	
	@Override
	public String apply(final Object context, final Object d, final int index) {
		String className =  "q" + index % 9 + "-9";
		return className;
	}
		
	
	//#end region
	
	
}
