package org.treez.javafxd3.d3.selection.comparator;



import java.util.Comparator;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.Value;

/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class ValueComparator implements Comparator<Object> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
		
	//#end region
	
	//#region CONSTRUCTORS
	
		public ValueComparator(JsEngine engine){
		this.engine = engine;
	}


	@Override
	public int compare(Object o1, Object o2) {		
		
		Value firstValue = ConversionUtil.convertObjectTo(o1,  Value.class, engine);
		Value secondValue = ConversionUtil.convertObjectTo(o2,  Value.class, engine);
		
		Integer d1 = firstValue.<Integer> as();
		Integer d2 = secondValue.<Integer> as();
		System.out.println("sorting: " + d1 + " " + d2);
		
		return d1.compareTo(d2);
	}
	
	//#end region
	
	//#region METHODS

	
	
	//#end region

}
