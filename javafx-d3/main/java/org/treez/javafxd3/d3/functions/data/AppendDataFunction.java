package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsObject;

public class AppendDataFunction implements DataFunction<Object> {

	//#region ATTRIBUTES

	private JsObject jsObjectToAppend;

	//#end region

	//#region CONSTRUCTORS	

	public AppendDataFunction(JavaScriptObject objectToAppend) {
		this.jsObjectToAppend = objectToAppend.getJsObject();
	}

	//#end region

	//#region METHODS

	@Override
	public Object apply(Object context, Object datum, int index) {
		return jsObjectToAppend.unwrap();
	}

	//#end region

}
