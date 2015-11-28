package com.github.javafxd3.d3.geom;

import java.util.List;

import com.github.javafxd3.d3.arrays.Array;
import com.github.javafxd3.d3.functions.DatumFunction;
import com.github.javafxd3.d3.wrapper.JavaScriptObject;

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

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Hull(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

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

		assertObjectIsNotAnonymous(xAccessor);

		String accessorName = createNewTemporaryInstanceName();

		JSObject d3JsObject = getD3();
		d3JsObject.setMember(accessorName, xAccessor);

		String command = "this.x(function(d, i) { return d3." + accessorName + ".apply(this,{datum:d},i); });";

		JSObject result = evalForJsObject(command);
		return new Hull(webEngine, result);
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

		assertObjectIsNotAnonymous(yAccessor);

		String accessorName = createNewTemporaryInstanceName();

		JSObject d3JsObject = getD3();
		d3JsObject.setMember(accessorName, yAccessor);

		String command = "this.y(function(d, i) { return d3." + accessorName + ".apply(this,{datum:d},i); });";

		JSObject result = evalForJsObject(command);
		return new Hull(webEngine, result);
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
	public <T> Array<T> apply(Array<T> vertices) {
		JSObject arrayObj = vertices.getJsObject();

		String tempVarName = createNewTemporaryInstanceName();

		JSObject d3JsObject = getD3();
		d3JsObject.setMember(tempVarName, arrayObj);

		String command = "this(d3." + tempVarName + ")";
		JSObject result = evalForJsObject(command);

		return new Array<T>(webEngine, result);
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
	public final <T> Array<T> apply(List<T> vertices) {
		return this.apply(Array.fromList(webEngine, vertices));
	}

}
