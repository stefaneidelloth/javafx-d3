package com.github.javafxd3.d3.layout;

import com.github.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A node in d3j's tree layout, see <a href="https:nodes">d3 docs on node</a>.
 * The node class is used in {@link Tree}, cluster, and partitions. The class
 * provides accessors for the nodes key attributes, its position, children,
 * parent, and depth.
 * 
 * 
 * 
 */
public class Node extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Node(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * @return the x coordinates
	 */
	public double x() {
		Double result = getMemberForDouble("x");
		return result;
		
	}

	/**
	 * sets the x coordinate
	 * @param x 
	 */
	public void x(double x) {
		String command = "this.x = " + x+";";
		eval(command);
	}

	/**
	 * @return the y coordinates
	 */
	public double y() {
		Double result = getMemberForDouble("y");
		return result;
	}

	/**
	 * sets the y coordinate
	 * @param y 
	 */
	public void y(double y) {
		String command = "this.y = " + y+";";
		eval(command);
	}
	
	//#end region
}
