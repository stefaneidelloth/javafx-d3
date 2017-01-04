package org.treez.javafxd3.d3.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.treez.javafxd3.d3.core.Selection;

/**
 * 
 */
public class Style {
	
	//#region ATTRIBUTES
	
	Map<String, String> styleValues = new HashMap<>();
	
	//#end region
	
	//#region CONSTRUCTORS	
	
	public Style(){	}
	
	//#end region
	
	//#region METHODS
	
	public void apply(Selection labelSelection) {
		for(String styleProperty: styleValues.keySet()){
			String value = styleValues.get(styleProperty);
			labelSelection.style(styleProperty, value);
		}		
	}
	
	//#end region

	//#region ACCESSORS	
	
	public void setProperty(String styleProperty, String value) {
		styleValues.put(styleProperty, value);		
	}

	
	
	//#end region

}
