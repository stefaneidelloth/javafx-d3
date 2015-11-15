package com.github.javafxd3.api.event;

import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.functions.JsFunction;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 * @param <T> 
 *
 */
public class Event<T> extends JavaScriptObject implements JsFunction {
	
	//#region ATTRIBUTES	

	protected T sourceEvent;    
	
	//#end region

	// #region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public Event(WebEngine webEngine){
		super(webEngine);
	}

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Event(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}
		
	// #end region
	
	//#region ACCESSORS
	
	/**
	 * @return
	 */
	public Selection getEventTarget(){
		JSObject result = call("this.eventTarget");
		return new Selection(webEngine, result);
	}
	
	//#end region

}