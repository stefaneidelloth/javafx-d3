package com.github.javafxd3.api.geom;

import java.util.List;

import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A <a href="http://en.wikipedia.org/wiki/Convex_hull">convex hull</a>.
 * 
 * @see <a href="http://bl.ocks.org/mbostock/4341699">this example</a>
 * 
 * 
 */
public class Hull extends JavaScriptObject {

	// #region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Hull(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);

	}

	// #end region

	// #region METHODS

	/**
	 * Sets the x-coordinate accessor.
	 * <p>
	 * The default accessor consider datum as a two element array and returns
	 * the first element.
	 * 
	 * @param xAccessor
	 *            the x accessor
	 * @return the current hull
	 */
	public Hull x(DatumFunction<Double> xAccessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .x(function(d, i) { return
		 * xAccessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 * 
		 */
	}

	/**
	 * Sets the y-coordinate accessor.
	 * <p>
	 * The default accessor consider datum as a two element array and returns
	 * the first element.
	 * 
	 * @param yAccessor
	 *            the y accessor
	 * @return the current hull
	 */

	public Hull y(DatumFunction<Double> yAccessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .y(function(d, i) { return
		 * yAccessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 * 
		 */
	}

	/**
	 * Returns the convex hull for the specified vertices array, using the
	 * current x- and y-coordinate accessors.
	 * <p>
	 * The returned convex hull is represented as an array containing a subset
	 * of the input vertices, arranged in counterclockwise order (for
	 * consistency with polygon.clip).
	 * <p>
	 * 
	 * @param vertices
	 *            the array of vertices
	 * @return the convex hull as an array of vertices
	 */
	public <T> T[] apply(T[] vertices) {
		throw new IllegalStateException("not yet implemented");
		// return this(vertices);
	}

	/**
	 * Returns the convex hull for the specified vertices array, using the
	 * current x- and y-coordinate accessors.
	 * <p>
	 * The returned convex hull is represented as an array containing a subset
	 * of the input vertices, arranged in counterclockwise order (for
	 * consistency with polygon.clip).
	 * <p>
	 * 
	 * @param vertices
	 *            the array of vertices
	 * @return the convex hull as an array of vertices
	 */
	public final <T> List<T> apply(List<T> vertices) {
		throw new IllegalStateException("not yet implemented");
		// return this.apply(Array.fromIterable(vertices)).asList();
	}

}
