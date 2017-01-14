package org.treez.javafxd3.d3.functions.callback;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DsvCallbackWrapper<A> implements DsvCallback<A> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainCallback<A> plainCallback = null;	
	
	private Class<A> elementClass;

	//#end region

	//#region CONSTRUCTORS

	public DsvCallbackWrapper(Class<A> elementClass, WebEngine webEngine, PlainCallback<A> plainCallback) {
		this.webEngine = webEngine;
		this.plainCallback = plainCallback;		
		this.elementClass = elementClass;
	}

	//#end region

	//#region METHODS

	@Override
	public void get(Object error, Object dataArrayObj) {
		
		JSObject jsDataArray = (JSObject) dataArrayObj;	
		
		Array<A> dataArray;
		boolean isJavaScriptObjectArray = JavaScriptObject.class.isAssignableFrom(elementClass);
		if(!isJavaScriptObjectArray){
			//use element objects directly
			dataArray = new Array<>(webEngine, jsDataArray);
		} else {
			//convert each element to the required class
			Array<JSObject> jsArray = new Array<>(webEngine, jsDataArray);
			List<A> convertedList = jsArray.asList(elementClass);
			dataArray = Array.fromListDirectly(webEngine, convertedList);
		}		
		
		plainCallback.call(dataArray);
	}

	//#end region

}
