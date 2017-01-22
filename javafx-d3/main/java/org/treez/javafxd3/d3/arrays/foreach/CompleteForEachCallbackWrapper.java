package org.treez.javafxd3.d3.arrays.foreach;

import org.treez.javafxd3.d3.core.JsEngine;

public class CompleteForEachCallbackWrapper<T> implements ForEachCallback<T>{
	
	private JsEngine engine;
	
	private ForEachCallback<T> wrappedCallback;	
	
	public CompleteForEachCallbackWrapper(JsEngine engine, ForEachCallback<T> wrappedCallback){
		this.wrappedCallback=wrappedCallback;
		this.engine = engine;
	}

	@Override
	public T forEach(Object context, Object element, int index, Object array){
	    Object jsContext = engine.toJsObjectIfNotSimpleType(context);
	    Object jsElement = engine.toJsObjectIfNotSimpleType(element);
		return wrappedCallback.forEach(jsContext, jsElement, index, array);		
	}	
	
}
