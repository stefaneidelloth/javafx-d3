package com.github.javafxd3.d3.wrapper;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class JsArrayMixed extends JavaScriptObject {
	
//#region CONSTRUCTORS
	
	 /**
	  * Constructor
     * @param webEngine
     */
    public JsArrayMixed(WebEngine webEngine) {
    	super(webEngine);
    }
	
    /**
     * Constructor
     * @param webEngine
     * @param wrappedJsObject
     */
    public JsArrayMixed(WebEngine webEngine, JSObject wrappedJsObject) {
    	super(webEngine);
    	setJsObject(wrappedJsObject);

    }
    
    //#end region
     
}
