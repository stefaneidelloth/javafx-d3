package org.treez.javafxd3.d3.layout;


import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.DelegatingDataFunction;
import org.treez.javafxd3.d3.wrapper.Sort;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Per the <a href="https:Layout">d3 API reference<link diagrams of trees using
 * the Reingoldâ€“Tilford â€œtidyâ€� algorithm. Like most other
 * layouts, the object returned by d3.layout.tree is both an object and a
 * function. That is: you can call the layout like any other function, and the
 * layout has additional methods that change its behavior. Like other classes in
 * D3, layouts follow the method chaining pattern where setter methods return
 * the layout itself, allowing multiple setters to be invoked in a concise
 * statement.
 * 
 * 
 * 
 */
public class Tree extends HierarchicalLayout {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Tree(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Sets the available layout size to the specified two-element array of
	 * numbers representing x and y. 
	 * 
	 * @param width 
	 * @param height 
	 * @return  this tree object
	 */
	public  Tree size(double width, double height) {
		String command = "this.size([ "+width+", "+height+" ]);";
		JSObject result = evalForJsObject(command);
		return new Tree(webEngine, result);
	}

	/**
	 * Returns the current tree size, which defaults to 1—1. Although the
	 * layout has a size in x and y, this represents an arbitrary coordinate
	 * system. For example, to produce a radial layout where the tree breadth
	 * (*x*) is measured in degrees, and the tree depth (*y*) is a radius r in
	 * pixels, say [360, r].
	 * 
	 * @return a two-element array representing the current size of the tree
	 */
	public Array<Double> size() {
		JSObject result = call("size");
		return new Array<Double>(webEngine, result);		
	}

	/**
	 * Sets a fixed size for each node as a two-element array of numbers
	 * representing x and y.
	 * 
	 * @param width
	 * @param height
	 * 
	 * @return this tree layout
	 */
	public  Tree nodeSize(double width, double height) {
		String command = "this.nodeSize([ "+width+", "+height+" ]);";
		JSObject result = evalForJsObject(command);
		return new Tree(webEngine, result);	
	}

	/**
	 * Returns the current node size, which defaults to null, meaning that the
	 * layout has an overall fixed size, which can be retrieved using
	 * {@link #size()}.
	 * 
	 * @return a two element array representing the current size of nodes in the
	 *         tree
	 */
	public Array<Double> nodeSize() {
		JSObject result = call("nodeSize");
		return new Array<Double>(webEngine, result);
	}

	/**
	 * Sets the sort order of sibling nodes for the layout using the specified
	 * comparator function. The comparator function is invoked for pairs of
	 * nodes, being passed the input data for each node. The default comparator
	 * is null, which disables sorting and uses tree traversal order. Sorting by
	 * the node's name or key is common and can be done easily using
	 * D3#ascending() or D3#descending().
	 * 
	 * @param sort a predefined sorting convention
	 * @return this tree object
	 */
	public Tree sort(Sort sort) {
		JSObject result = call("sort", sort.getJsObject());
		return new Tree(webEngine, result);		
	}

	/**
	 * Uses the specified function to compute separation between neighboring
	 * nodes. The separation function is passed two neighboring nodes a and b,
	 * and must return the desired separation between nodes. The nodes are
	 * typically siblings, though the nodes may also be cousins (or even more
	 * distant relations) if the layout decides to place such nodes adjacent.
	 * See <a href="https:separation" >wiki</a> for examples.
	 * @param sort 
	 * 
	 *            a datum function describing how to calculate separation of
	 *            nodes
	 * @return this tree object
	 */
	public Tree separation(Sort sort) {
		JSObject result = call("separation", sort.getJsObject());
		return new Tree(webEngine, result);	
	}

	/**
	 * Sets the specified children accessor function. The default children
	 * accessor function assumes the input data is an object with a children
	 * array. The children accessor is first invoked for root node in the
	 * hierarchy. If the accessor returns null, then the node is assumed to be a
	 * leaf node at the layout traversal terminates. Otherwise, the accessor
	 * should return an array of data elements representing the child nodes.
	 * 
	 * @param df
	 *            a datum function describing how to compute children
	 * @return this tree object
	 */
	public  Tree children(DataFunction<Array<Node>> function) {		
		String functionName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(functionName, function);
		
		String command = "this.children(function(d) { return d3."+functionName +".apply(this,{datum:d},0);})";
		JSObject result = evalForJsObject(command);
		
		if(result==null){
			return null;
		}
		
		return new Tree(webEngine, result);		
	}

	/**
	 * Sets the value accessor to the specified function. The value accessor is
	 * invoked for each input data element, and must return a number
	 * representing the numeric value of the node. This value has no effect on
	 * the tree layout, but is generic functionality provided by hierarchy
	 * layouts.
	 * 
	 * @param df
	 *            a datum function describing how to access node values
	 * @return this tree object
	 */
	public  Tree value(DataFunction<?> function) {
		
		assertObjectIsNotAnonymous(function);
		
		String functionName = createNewTemporaryInstanceName();
		JSObject d3JsObject = getD3();
		d3JsObject.setMember(functionName, function);
		
		String command = "this.value(function(d, i) { return d3."+functionName+".apply(this,{datum:d},i);})";
		JSObject result = evalForJsObject(command);
		return new Tree(webEngine, result);	     
	}

	/**
	 * Returns the current value accessor which defaults to null, meaning that
	 * the value attribute is not computed.
	 * 
	 * @return the current datum function registered for calculating node values
	 */
	public DataFunction<?> value() {
		JSObject result = call("value");
		DataFunction<?> datumFunction = new DelegatingDataFunction(result);
		return datumFunction;		
	}
}
