package org.treez.javafxd3.d3.arrays.foreach;

import org.treez.javafxd3.d3.core.JsEngine;

/**
 * Wraps a ForEachObjectDelegate in a named class so that it can 
 * be used in the JavaScript world (that would not work with anonymous functions directly).
 */
public class ForEachObjectDelegateWrapper implements ForEachObjectDelegate{
	
	private JsEngine engine;
	
	private ForEachObjectDelegate wrappedDelegate;	
	
	public ForEachObjectDelegateWrapper(JsEngine engine, ForEachObjectDelegate wrappedDelegate){
		this.engine = engine;
		this.wrappedDelegate=wrappedDelegate;
	}

	@Override
	public void process(Object element) {
		Object jsElement = engine.toJsObjectIfNotSimpleType(element);
		wrappedDelegate.process(jsElement);		
	}	
	
}
