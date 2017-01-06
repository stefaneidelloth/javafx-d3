package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;




public class OddEvenElementDataFunction implements DataFunction<Element> {
	
	//#region ATTRIBUTES
		
	private WebEngine webEngine;

	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public OddEvenElementDataFunction(WebEngine webEngine){
		this.webEngine = webEngine;
		
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Element apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) context;
		Element element = new Element(webEngine, jsObject);

		Element result = (index % 2) == 0 ? element : null;		 
		return result;
	}
	
	//#end region

}
