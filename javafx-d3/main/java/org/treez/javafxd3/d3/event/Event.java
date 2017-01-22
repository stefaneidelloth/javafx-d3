package org.treez.javafxd3.d3.event;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.JsFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Event extends JavaScriptObject implements JsFunction {
		

	//#region CONSTRUCTORS

	public Event(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);		
	}
		
	//#end region
	
	//#region ACCESSORS
	
	/**
	 * @return
	 */
	public Selection getEventTarget(){		
		JsObject result = getMember("target");
		return new Selection(engine, result);
	}

	public Event sourceEvent() {
		JsObject result = call("this.sourceEvent");
		return new Event(engine, result);		
	}

	public void stopPropagation() {
		call("this.stopPropagation()");		
	}
	
	//#end region

}