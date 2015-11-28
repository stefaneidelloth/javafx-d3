package com.github.javafxd3.d3.layout;

import com.github.javafxd3.d3.core.Value;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;
import com.github.javafxd3.d3.wrapper.Sort;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Chord extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Chord(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @param padding
	 * @return
	 */
	public Chord padding(double padding) {
		JSObject result = call("padding", padding);
		return new Chord(webEngine, result);
	}

	/**
	 * @param sort
	 * @return
	 */
	public Chord sortSubgroups(Sort sort) {
		JSObject sortObj = sort.getJsObject();
		JSObject result = call("sortSubgroups", sortObj);
		return new Chord(webEngine, result);
	}

	/**
	 * @return
	 */
	public JSObject chords() {
		JSObject result = getMember("chords");
		return result;
	}

	/**
	 * 
	 *
	 */
	public static class Group extends JavaScriptObject {

		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param webEngine
		 * @param wrappedJsObject
		 */
		public Group(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine);
			setJsObject(wrappedJsObject);
		}

		//#end region

		//#region METHODS

		/**
		 * @return
		 */
		public int index() {
			Integer result = getMemberForInteger("index");
			return result;			
		}

		/**
		 * @return
		 */
		public double startAngle() {
			Double result = getMemberForDouble("startAngle");
			return result;			
		}

		/**
		 * @return
		 */
		public double endAngle() {
			Double result = getMemberForDouble("endAngle");
			return result;
		}

		/**
		 * @return
		 */
		public double value() {
			Double result = getMemberForDouble("value");
			return result;
		}

		//#end region
	}

	/**
	 * 
	 *
	 */
	public static class ChordItem extends Value {
		
		//#region CONSTRUCTORS

		/**
		 * Constructor
		 * 
		 * @param webEngine
		 * @param wrappedJsObject
		 */
		public ChordItem(WebEngine webEngine, JSObject wrappedJsObject) {
			super(webEngine, wrappedJsObject);
		}

		//#end region

		//#region METHODS

		/**
		 * @return
		 */
		public Group source() {
			JSObject result = getMember("source");
			return new Group(webEngine, result);			
		}

		/**
		 * @return
		 */
		public Group target() {
			JSObject result = getMember("target");
			return new Group(webEngine, result);	
		}

		//#end region
	}

	//#end region
}
