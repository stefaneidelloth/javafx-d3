package org.treez.javafxd3.d3.functions.data;

import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import netscape.javascript.JSObject;

public class AppendDataFunction implements DataFunction<JSObject> {

	//#region ATTRIBUTES

	private JSObject jsObjectToAppend;

	//#end region

	//#region CONSTRUCTORS	

	public AppendDataFunction(JavaScriptObject objectToAppend) {
		this.jsObjectToAppend = objectToAppend.getJsObject();
	}

	//#end region

	//#region METHODS

	@Override
	public JSObject apply(Object context, Object datum, int index) {
		return jsObjectToAppend;
	}

	//#end region

}
