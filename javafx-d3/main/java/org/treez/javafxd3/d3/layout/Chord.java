package org.treez.javafxd3.d3.layout;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.d3.wrapper.Sort;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 *
 */
public class Chord extends JavaScriptObject {

	//#region CONSTRUCTORS

	public Chord(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public Chord padding(double padding) {
		JsObject result = call("padding", padding);
		return new Chord(engine, result);
	}

	public Chord sortSubgroups(Sort sort) {
		JsObject sortObj = sort.getJsObject();
		JsObject result = call("sortSubgroups", sortObj);
		return new Chord(engine, result);
	}

	public Chord matrix(Double[][] matrix) {
		String arrayString = ArrayUtils.createArrayString(matrix);
		JsObject result = evalForJsObject("this.matrix(" +  arrayString + ")");
		if (result == null) {
			return null;
		}
		return new Chord(engine, result);
	}

	public List<Group> groups() {
		JsObject result = call("groups");
		if(result==null){
			return null;
		}
		Array<JsObject> array= new Array<>(engine, result);
		
		List<Group> groups = new ArrayList<>();
		array.forEach((object)->{
			JsObject jsGroup = (JsObject) object;			
			Group group = new Group(engine, jsGroup);
			groups.add(group);
		});
		return groups;
		
		
	}

	public Array<JsObject> chords() {
		JsObject result = call("chords");
		if(result==null){
			return null;
		}
		return new Array<>(engine, result);
	}

	//#end region

	//#region CLASSES

	public static class Group extends JavaScriptObject {

		//#region CONSTRUCTORS

		public Group(JsEngine engine, JsObject wrappedJsObject) {
			super(engine);
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

		public ChordItem(JsEngine engine, JsObject wrappedJsObject) {
			super(engine, wrappedJsObject);			
		}

		//#end region

		//#region METHODS

		public Group source() {
			JsObject result = getMember("source");
			
			if(result==null){
				return null;
			}
			
			return new Group(engine, result);
		}

		public Group target() {
			JsObject result = getMember("target");
			
			if(result==null){
				return null;
			}
			
			return new Group(engine, result);
		}

		//#end region
	}

	//#end region
}
