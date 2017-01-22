package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 *
 */
public class JsArrayMixed extends JavaScriptObject {
	
//#region CONSTRUCTORS
	
	 /**
	  * Constructor
     * @param engine
     */
    public JsArrayMixed(JsEngine engine) {
    	super(engine);
    }
	
    /**
     * Constructor
     * @param engine
     * @param wrappedJsObject
     */
    public JsArrayMixed(JsEngine engine, JsObject wrappedJsObject) {
    	super(engine);
    	setJsObject(wrappedJsObject);

    }
    
    //#end region
     
}
