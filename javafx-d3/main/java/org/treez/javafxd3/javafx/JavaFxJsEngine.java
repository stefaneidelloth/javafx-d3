package org.treez.javafxd3.javafx;

import org.treez.javafxd3.d3.core.JsEngine;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class JavaFxJsEngine implements JsEngine {

	//#region ATTRIBUTES

	private WebEngine wrappedJsEngine;

	//#end region

	//#region CONSTRUCTORS

	public JavaFxJsEngine(WebEngine wrappedJsEngine) {
		this.wrappedJsEngine = wrappedJsEngine;
	}

	//#end region

	//#region METHODS

	@Override
	public Object executeScript(String script) {
		Object result = wrappedJsEngine.executeScript(script);
		return JavaFxJsObject.wrapIfIsJSObject(result);
	}

	@Override
	public Object toJsObjectIfNotSimpleType(Object argument) {
		
		boolean isJSObject = argument instanceof JSObject;
		if(isJSObject){
			JSObject jsArgument = (JSObject) argument;
			return new JavaFxJsObject(jsArgument);
		}
		return argument;		
	}

	//#end region

}
