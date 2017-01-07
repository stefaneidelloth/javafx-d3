package org.treez.javafxd3.d3.layout;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.d3.wrapper.Sort;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Chord extends JavaScriptObject {

	//#region CONSTRUCTORS

	public Chord(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public Chord padding(double padding) {
		JSObject result = call("padding", padding);
		return new Chord(webEngine, result);
	}

	public Chord sortSubgroups(Sort sort) {
		JSObject sortObj = sort.getJsObject();
		JSObject result = call("sortSubgroups", sortObj);
		return new Chord(webEngine, result);
	}

	public Chord matrix(Double[][] matrix) {
		String arrayString = ArrayUtils.createArrayString(matrix);
		JSObject result = evalForJsObject("this.matrix(" +  arrayString + ")");
		if (result == null) {
			return null;
		}
		return new Chord(webEngine, result);
	}

	public List<Group> groups() {
		JSObject result = call("groups");
		if(result==null){
			return null;
		}
		Array<JSObject> array= new Array<>(webEngine, result);
		
		List<Group> groups = new ArrayList<>();
		array.forEach((object)->{
			JSObject jsGroup = (JSObject) object;			
			Group group = new Group(webEngine, jsGroup);
			groups.add(group);
		});
		return groups;
		
		
	}

	public Array<JSObject> chords() {
		JSObject result = call("chords");
		if(result==null){
			return null;
		}
		return new Array<>(webEngine, result);
	}

	//#end region

	//#region CLASSES

	public static class Group extends JavaScriptObject {

		//#region CONSTRUCTORS

		public Group(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine);
			setJsObject(wrappedJsObject);			
		}

		//#end region

		//#region METHODS

		public int index() {
			Integer result = getMemberForInteger("index");
			return result;
		}

		public double startAngle() {
			Double result = getMemberForDouble("startAngle");
			return result;
		}

		public double endAngle() {
			Double result = getMemberForDouble("endAngle");
			return result;
		}

		public double value() {
			Double result = getMemberForDouble("value");
			return result;
		}

		//#end region
	}

	public static class ChordItem extends Value {

		//#region CONSTRUCTORS

		public ChordItem(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine, wrappedJsObject);			
		}

		//#end region

		//#region METHODS

		public Group source() {
			JSObject result = getMember("source");
			
			if(result==null){
				return null;
			}
			
			return new Group(webEngine, result);
		}

		public Group target() {
			JSObject result = getMember("target");
			
			if(result==null){
				return null;
			}
			
			return new Group(webEngine, result);
		}

		//#end region
	}

	//#end region
}
