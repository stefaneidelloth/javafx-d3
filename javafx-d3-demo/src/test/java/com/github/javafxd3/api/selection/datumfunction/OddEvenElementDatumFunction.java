package com.github.javafxd3.api.selection.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;




public class OddEvenElementDatumFunction implements DatumFunction<Element> {
	
	//#region ATTRIBUTES
		
	private WebEngine webEngine;

	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public OddEvenElementDatumFunction(WebEngine webEngine){
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
