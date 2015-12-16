package org.treez.javafxd3.d3.selection.comparator;



import java.util.Comparator;

import org.treez.javafxd3.d3.core.Value;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;



/**
 * A datum function that asserts that the datum equals an increasing
 * counting index, starting with 1.
 *
 */
public class ValueComparator implements Comparator<Object> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
		
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ValueComparator(WebEngine webEngine){
		this.webEngine = webEngine;
	}


	@Override
	public int compare(Object o1, Object o2) {
		
		JSObject firstObject = (JSObject) o1;
		JSObject secondObject = (JSObject) o2;
		
		Value firstValue = new Value(webEngine, firstObject);
		Value secondValue = new Value(webEngine, secondObject);
		
		Integer d1 = firstValue.<Integer> as();
		Integer d2 = secondValue.<Integer> as();
		System.out.println("sorting: " + d1 + " " + d2);
		
		return d1.compareTo(d2);
	}
	
	//#end region
	
	//#region METHODS

	
	
	//#end region

}
