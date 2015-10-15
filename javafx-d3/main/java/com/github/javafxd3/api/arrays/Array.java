package com.github.javafxd3.api.arrays;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Array<T> extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Array(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);	
		setJsObject(wrappedJsObject);
	}
	
	//#end region

	
	
	/**
	 * Creates an Array for the given list of objects
	 * @param <L>
	 * @param webEngine
	 * @param data
	 * @return
	 */
	public static <L> Array<L> fromList(WebEngine webEngine, List<L> data) {
		
		//store data as temporary array
		JSObject d3Obj = (JSObject) webEngine.executeScript("d3");
		String varName = "data__temp__var";
		int length = data.size();
		String command = "d3." + varName + " = new Array(" + length + ");";
		Object res = d3Obj.eval(command);
		JSObject var = (JSObject) d3Obj.getMember(varName);
		for(int index=0;index<length;index++){
			
			Object value = data.get(index);
			boolean isJavaScriptObject = value instanceof JavaScriptObject;
			if (isJavaScriptObject){
				//unwrap java script value
				//JavaScriptObject obj = (JavaScriptObject) value;
				//value = obj.getJsObject();
			}			
			
			var.setSlot(index, value);
		}
		
		//get result
		JSObject result = (JSObject) d3Obj.getMember(varName);
		
		//delete temp var
		//d3Obj.removeMember(varName);
		
		//return result as array
		return new Array<L>(webEngine, result);		
	}

	/**
	 * Creates an Array from the given Double array
	 * @param webEngine
	 * @param data
	 * @return
	 */
	public static Array<Double> fromDoubles(WebEngine webEngine, Double[] data) {
		
		//convert Double array to string command
		List<String> dataList = new ArrayList<>();
		for(Double value:data){
			dataList.add(""+value);
		}
		String listString = String.join(",", dataList);		
		String command = "var temp__var = [" + listString + "];";
		webEngine.executeScript(command);
		
		//execute command and return result as Array		
		JSObject result = (JSObject) webEngine.executeScript("temp__var");
		String debugRes = result.toString();
		return new Array<Double>(webEngine, result);
				
	}

	public int length() {
		int result = getMemberForInteger("length");
		return result;				
	}

	public static Array fromObjects(WebEngine webEngine, JavaScriptObject first, JavaScriptObject second) {
		D3 d3 = new D3(webEngine);
		JSObject d3Obj = d3.getJsObject();
		String firstVarName = "temp__var__1";
		String secondVarName = "temp__var__2";
		
		JSObject firstObj = first.getJsObject();
		JSObject secondObj = second.getJsObject();
		
		d3Obj.setMember(firstVarName, firstObj);
		d3Obj.setMember(secondVarName, secondObj);
		
		String command = "["+firstVarName + "," + secondVarName+"]";
		JSObject result = d3.evalForJsObject(command);
		
		d3Obj.removeMember(firstVarName);
		d3Obj.removeMember(secondVarName);
		
		
		return new Array(webEngine, result);		
		
		
	}

	

}
