package org.treez.javafxd3.d3.functions.callback;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvCallback;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DsvCallbackWrapper<A> implements DsvCallback<A> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainCallback<A> plainCallback = null;	

	//#end region

	//#region CONSTRUCTORS

	public DsvCallbackWrapper(WebEngine webEngine, PlainCallback<A> plainCallback) {
		this.webEngine = webEngine;
		this.plainCallback = plainCallback;		
	}

	//#end region

	//#region METHODS

	@Override
	public void get(Object error, Object dataArrayObj) {		
		JSObject jsDataArray = (JSObject) dataArrayObj;		
		Array<A> dataArray = new Array<>(webEngine, jsDataArray);			
		plainCallback.call(dataArray);
	}

	//#end region

}
