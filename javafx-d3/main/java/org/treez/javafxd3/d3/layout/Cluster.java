package org.treez.javafxd3.d3.layout;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

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
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Cluster(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
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
		JsObject result = evalForJsObject(command);
		return new Cluster(engine, result);
	};

	//#end region

}
