package org.treez.javafxd3.javafx;

import org.treez.javafxd3.d3.core.JsObject;
import org.w3c.dom.Element;

import netscape.javascript.JSObject;

public class JavaFxJsObject implements JsObject {
	
	//#region ATTRIBUTES
	
	private JSObject wrappedJSObject;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public JavaFxJsObject(JSObject wrappedJSObject){
		this.wrappedJSObject = wrappedJSObject;
	}	
	
	//#end region
	
	//#region METHODS
	
	public static Object wrapIfIsJSObject(Object result) {
		boolean isJSObject = result instanceof JSObject;
		if(isJSObject){
			return new JavaFxJsObject((JSObject) result);
		}
		return result;
	}
	
	@Override
	public Object call(String methodName, Object... args) {
		
		Object[] unwrappedArgs = new Object[args.length];
		for(int index=0; index < args.length; index++){
			Object arg = args[index];
			Object unwrappedArg = unwrapIfIsJsObject(arg);
			unwrappedArgs[index]= unwrappedArg;			
		}		
		
		Object result = wrappedJSObject.call(methodName, unwrappedArgs);
		return wrapIfIsJSObject(result);				
	}	

	@Override
	public Object eval(String command) {
		Object result = wrappedJSObject.eval(command);
		return wrapIfIsJSObject(result);	
	}

	@Override
	public Object getMember(String name) {
		Object result = wrappedJSObject.getMember(name);		
		return wrapIfIsJSObject(result);	
	}

	@Override
	public void setMember(String name, Object value) {
		Object valueObj = unwrapIfIsJsObject(value);
		wrappedJSObject.setMember(name, valueObj);		
	}	

	@Override
	public void removeMember(String name) {
		wrappedJSObject.removeMember(name);			
	}

	@Override
	public Object getSlot(int index) {
		Object result = wrappedJSObject.getSlot(index);
		return wrapIfIsJSObject(result);
	}

	@Override
	public void setSlot(int index, Object value) {
		Object valueObj = unwrapIfIsJsObject(value);
		wrappedJSObject.setSlot(index, valueObj);		
	}
	
	@Override
	public Object unwrap() {
		return wrappedJSObject;
	}	
	
	private Object unwrapIfIsJsObject(Object value) {
		boolean isJsObject = value instanceof JsObject;
		if(isJsObject){
			JsObject jsObject = (JsObject) value;
			return jsObject.unwrap();			
		}
		return value;
	}
	
	@Override 
	public int hashCode(){
		return wrappedJSObject.hashCode();
	}
	
	@Override 
	public boolean equals(Object otherObject){
		if(otherObject==this){
			return true;
		}
		boolean isJavaFxJsObject = otherObject instanceof JavaFxJsObject;
		if(!isJavaFxJsObject){
			return false;
		}
		JavaFxJsObject otherJsObject = (JavaFxJsObject) otherObject;
		JSObject otherWrappedJSObject = (JSObject) otherJsObject.unwrap();		
		return wrappedJSObject.equals(otherWrappedJSObject);
	}
	
	@Override
	public String toString(){
		return wrappedJSObject.toString();
	}

	@Override
	public boolean isElement() {
		return wrappedJSObject instanceof Element;
	}
	
	//#end region

}
