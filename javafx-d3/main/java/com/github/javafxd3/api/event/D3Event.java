package com.github.javafxd3.api.event;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 * @param <T> 
 *
 */
public class D3Event<T> extends Event<T> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public D3Event(WebEngine webEngine, JSObject wrappedJsObject) {
        super(webEngine, wrappedJsObject);
    }
	
	//#end region

	

    /**
     * Returns the source event that triggered this instance.
     * <p>
     * This
     *
     * @return the source event
     */
    public T sourceEvent() {
		return this.sourceEvent;
    };
}
