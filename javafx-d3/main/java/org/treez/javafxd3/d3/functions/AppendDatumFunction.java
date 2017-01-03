package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import netscape.javascript.JSObject;

public class AppendDatumFunction implements DatumFunction<JSObject> {

	//#region ATTRIBUTES

	private JSObject jsObjectToAppend;

	//#end region

	//#region CONSTRUCTORS	

	public AppendDatumFunction(JavaScriptObject objectToAppend) {
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
