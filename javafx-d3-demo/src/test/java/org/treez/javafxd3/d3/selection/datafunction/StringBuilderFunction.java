package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A DataFunction that appends strings to a wrapped StringBuilder
 */
public class StringBuilderFunction implements DataFunction<Void> {
	
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
		stringBuilder.append(element.getText());
		return null;
	}
	
	//#end region

}
