package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 *
 */
public class JsArrayNumber extends JavaScriptObject {
	
//#region CONSTRUCTORS
	
	 /**
	  * Constructor
     * @param engine
     */
    public JsArrayNumber(JsEngine engine) {
    	super(engine);
    }
	
    /**
     * Constructor
     * @param engine
     * @param wrappedJsObject
     */
    public JsArrayNumber(JsEngine engine, JsObject wrappedJsObject) {
    	super(engine);
    	setJsObject(wrappedJsObject);
    }
    
    //#end region
      
}
