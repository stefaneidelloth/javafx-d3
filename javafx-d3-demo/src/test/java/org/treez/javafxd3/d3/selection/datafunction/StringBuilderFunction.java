package org.treez.javafxd3.d3.selection.datafunction;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

/**
 * A DataFunction that appends strings to a wrapped StringBuilder
 */
public class StringBuilderFunction implements DataFunction<Void> {
	
	//#region ATTRIBUTES
	
	private StringBuilder stringBuilder;
	private JsEngine engine;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	 public StringBuilderFunction(JsEngine engine, StringBuilder stringBuilder){
		 this.engine = engine;
		this.stringBuilder = stringBuilder;
	}
	//#end region
	
	//#region METHODS

	@Override
	public Void apply(Object context, Object datum, int index) {		
		Element element = ConversionUtil.convertObjectTo(context,  Element.class, engine);
		stringBuilder.append(element.getText());
		return null;
	}
	
	//#end region

}
