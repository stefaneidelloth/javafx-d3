package com.github.javafxd3.api.selection.datumfunction;

import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A DatumFunction that appends strings to a wrapped StringBuilder
 */
public class StringBuilderFunction implements DatumFunction<Void> {
	
	//#region ATTRIBUTES
	
	private StringBuilder stringBuilder;
	private WebEngine webEngine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public StringBuilderFunction(WebEngine webEngine, StringBuilder stringBuilder){
		 this.webEngine = webEngine;
		this.stringBuilder = stringBuilder;
	}
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {
		JSObject jsObject = (JSObject) context;
		Element element = new Element(webEngine, jsObject);
		stringBuilder.append(element.getInnerText());
		return null;
	}
	
	//#end region

}
