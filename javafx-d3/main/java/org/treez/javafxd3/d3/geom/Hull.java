package org.treez.javafxd3.d3.geom;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A <a href="http://en.wikipedia.org/wiki/Convex_hull">convex hull</a>.
 * 
 * @see <a href="http://bl.ocks.org/mbostock/4341699">this example</a>
 * 
 * 
 */
public class Hull extends JavaScriptObject {

	//#region CONSTRUCTORS
	
	public Hull(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
	public Hull x(DataFunction<Double> xAccessor) {

		assertObjectIsNotAnonymous(xAccessor);

		String accessorName = createNewTemporaryInstanceName();

		JsObject d3JsObject = getD3();
		d3JsObject.setMember(accessorName, xAccessor);

		String command = "this.x(function(d, i) { return d3." + accessorName + ".apply(this,d,i); });";

		JsObject result = evalForJsObject(command);
		
	
		if (result == null) {
			return null;
		}
		return new Hull(engine, result);
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

	public Hull y(DataFunction<Double> yAccessor) {

		assertObjectIsNotAnonymous(yAccessor);

		String accessorName = createNewTemporaryInstanceName();

		JsObject d3JsObject = getD3();
		d3JsObject.setMember(accessorName, yAccessor);

		String command = "this.y(function(d, i) { return d3." + accessorName + ".apply(this,d,i); });";

		JsObject result = evalForJsObject(command);
		
		
		if (result == null) {
			return null;
		}
		return new Hull(engine, result);
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
		JsObject arrayObj = vertices.getJsObject();

		String tempVarName = createNewTemporaryInstanceName();

		JsObject d3JsObject = getD3();
		d3JsObject.setMember(tempVarName, arrayObj);

		String command = "this(d3." + tempVarName + ")";
		JsObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(tempVarName);

		if (result == null) {
			return null;
		}

		return new Array<T>(engine, result);
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
		return this.apply(Array.fromList(engine, vertices));
	}

}
