package com.github.javafxd3.api.layout;

import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A link in d3js' tree layout, see <a href="https:links">d3 docs on link</a>.
 * Provides accessors and setters for a link's two key attributes source and
 * target.
 * 
 * 
 * 
 */
public class Link extends JavaScriptObject {

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Link(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	// #end region

	// #region METHODS

	/**
	 * Create a basic link object starting at one coordinate and ending at
	 * another
	 * 
	 * @param source
	 *            starting coordinates
	 * @param target
	 *            ending coordinates
	 * 
	 * @return the link object
	 */
	public static Link create(Coords source, Coords target) {

		throw new IllegalStateException("not yet implemented");

		/*
		 * 
		 * return { source : source, target : target };
		 */
	}

	/**
	 * @return the end node
	 */
	public Node target() {
		JSObject result = getMember("target");
		return new Node(webEngine, result);

	}

	/**
	 * @return the start node
	 */
	public Node source() {
		JSObject result = getMember("source");
		return new Node(webEngine, result);
	}
}
