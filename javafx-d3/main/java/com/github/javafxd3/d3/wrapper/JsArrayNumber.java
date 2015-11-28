package com.github.javafxd3.d3.wrapper;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class JsArrayNumber extends JavaScriptObject {
	
//#region CONSTRUCTORS
	
	 /**
	  * Constructor
     * @param webEngine
     */
    public JsArrayNumber(WebEngine webEngine) {
    	super(webEngine);
    }
	
    /**
     * Constructor
     * @param webEngine
     * @param wrappedJsObject
     */
    public JsArrayNumber(WebEngine webEngine, JSObject wrappedJsObject) {
    	super(webEngine);
    	setJsObject(wrappedJsObject);
    }
    
    //#end region
      
}
