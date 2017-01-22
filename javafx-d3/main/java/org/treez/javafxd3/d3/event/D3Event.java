package org.treez.javafxd3.d3.event;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 * @param <T> 
 *
 */
public class D3Event extends Event {
			
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param engine
	 * @param wrappedJsObject
	 */
	public D3Event(JsEngine engine, JsObject wrappedJsObject) {
        super(engine, wrappedJsObject);
    }
	
	//#end region

	
}
