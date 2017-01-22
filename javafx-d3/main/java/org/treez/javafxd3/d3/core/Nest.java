package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;

/**
 * Nesting allows elements in an array to be grouped into a hierarchical tree
 * structure; think of it like the GROUP BY operator in SQL, except you can have
 * multiple levels of grouping, and the resulting output is a tree rather than a
 * flat table.
 * <p>
 * The levels in the tree are specified by key functions. The leaf
 * nodes of the tree can be sorted by value, while the internal nodes can be
 * sorted by key.
 * <p>
 * An optional rollup function will collapse the elements in each
 * leaf node using a summary function.
 * <p>
 * The nest operator (the object returned by
 *  D3#nest(}) is reusable, and does not retain any references to the data that is
 * nested.
 * <p>
 * For example, consider the following tabular data structure of Barley yields,
 * from various sites in Minnesota during 1931-2:
 * 
 * var yields = [{yield: 27.00, variety: "Manchuria", year: 1931, site:
 * "University Farm"}, {yield: 48.87, variety: "Manchuria", year: 1931, site:
 * "Waseca"}, {yield: 27.43, variety: "Manchuria", year: 1931, site: "Morris"},
 * ...] To facilitate visualization, it may be useful to nest the elements first
 * by year, and then by variety, as follows:
 * 
 * var nest = d3.nest() .key(function(d) { return d.year; }) .key(function(d) {
 * return d.variety; }) .entries(yields); This returns a nested array. Each
 * element of the outer array is a key-values pair, listing the values for each
 * distinct key:
 * 
 * [{key: 1931, values: [ {key: "Manchuria", values: [ {yield: 27.00, variety:
 * "Manchuria", year: 1931, site: "University Farm"}, {yield: 48.87, variety:
 * "Manchuria", year: 1931, site: "Waseca"}, {yield: 27.43, variety:
 * "Manchuria", year: 1931, site: "Morris"}, ...]}, {key: "Glabron", values: [
 * {yield: 43.07, variety: "Glabron", year: 1931, site: "University Farm"},
 * {yield: 55.20, variety: "Glabron", year: 1931, site: "Waseca"}, ...]}, ...]},
 * {key: 1932, values: ...}] The nested form allows easy iteration and
 * generation of hierarchical structures in SVG or HTML.
 * 
 * 
 * 
 */
public class Nest extends JavaScriptObject {
	
	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * @param engine
	 */
	public Nest(JsEngine engine) {
		super(engine);
	}

	//#end region

}
