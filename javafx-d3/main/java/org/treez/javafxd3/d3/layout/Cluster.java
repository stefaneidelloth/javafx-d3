package org.treez.javafxd3.d3.layout;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * The cluster layout produces dendrograms: node-link diagrams that place leaf
 * nodes of the tree at the same depth.
 * <p>
 * For example, a cluster layout can be used to organize software classes in a
 * package hierarchy:
 * <img src="https://github.com/mbostock/d3/wiki/cluster.png"/>
 * <p>
 * Like other classes in D3, layouts follow the method chaining pattern where
 * setter methods return the layout itself, allowing multiple setters to be
 * invoked in a concise statement.
 * 
 * 
 * 
 */
public class Cluster extends HierarchicalLayout {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Cluster(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}
	
	//#end region

	//#region METHODS

	/**
	 * @param width
	 * @param height
	 * @return
	 */
	public  Cluster size(int width, int height){
		String command = "this.size(["+width+", "+height+"]);";
		JSObject result = evalForJsObject(command);
		return new Cluster(webEngine, result);
	};

	//#end region

}
