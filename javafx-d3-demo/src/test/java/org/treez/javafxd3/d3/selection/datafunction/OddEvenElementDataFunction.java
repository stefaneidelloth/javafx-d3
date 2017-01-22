package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

public class OddEvenElementDataFunction implements DataFunction<Element> {
	
	//#region ATTRIBUTES
		
	private JsEngine engine;	
	
	//#end region
	
	//#region CONSTRUCTORS	

	public OddEvenElementDataFunction(JsEngine engine){
		this.engine = engine;		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Element apply(Object context, Object datum, int index) {		
		Element element = ConversionUtil.convertObjectTo(context,  Element.class, engine);
		Element result = (index % 2) == 0 ? element : null;		 
		return result;
	}
	
	//#end region

}
