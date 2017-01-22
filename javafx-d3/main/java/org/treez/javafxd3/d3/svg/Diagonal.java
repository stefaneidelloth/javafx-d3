package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.layout.Node;
import org.treez.javafxd3.d3.layout.Tree;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Diagonal generator. Assumes the input data is an object with named attributes
 * matching the accessors {@link Diagonal#source()} and
 * {@link Diagonal#target()}. The returned function generates the path data for
 * a cubic Bezier connecting the source and target points; the tangents are
 * specified to produce smooth fan-in and fan-out when connecting nodes, as in a
 * node-link diagram.
 * 
 * Diagonals default to Cartesian orientations, but can be used in radial and
 * other orientations using {@link Diagonal#projection(DataFunction)}.
 */
public class Diagonal extends PathDataGenerator {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Diagonal(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * The projection converts the starting or ending point returned by the
	 * source and target accessors {@link #source()} and {@link #target()},
	 * returning a two-element array of numbers. The default accessor assumes
	 * that the input point is an object with x and y attributes.
	 * 
	 * The default accessor is thus compatible with D3's various {@link Node}
	 * layouts, including {@link Tree}, partition and cluster. For example, to
	 * produce a radial diagonal see the
	 * <a href= "https:diagonal_projection" >d3 API reference</a>
	 * 
	 * The projection is invoked in a similar manner as other value functions in
	 * D3. The function is passed two arguments, the current source or target
	 * point (derived from the current data, d) and the current index (i).
	 * 
	 * @return the accessor function registered with the diagonal generator
	 */
	public Diagonal projection() {
		JsObject result = call("projection");
		if(result==null){
			return null;
		}
		return new Diagonal(engine, result);		
	}

	/**
	 * The projection converts the starting or ending point returned by the
	 * source and target accessors {@link #source()} and {@link #target()},
	 * returning a two-element array of numbers. The default accessor assumes
	 * that the input point is an object with x and y attributes.
	 * 
	 * The default accessor is thus compatible with D3's various {@link Node}
	 * layouts, including {@link Tree}, partition and cluster. For example, to
	 * produce a radial diagonal see the
	 * <a href= "https:diagonal_projection" >d3 API reference</a>
	 * 
	 * The projection is invoked in a similar manner as other value functions in
	 * D3. The function is passed two arguments, the current source or target
	 * point (derived from the current data, d) and the current index (i).
	 * 
	 * @param df
	 *            datum function
	 * @return this diagonal object
	 */
	public Diagonal projection(DataFunction<?> function) {
		
		assertObjectIsNotAnonymous(function);

		String funcName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(funcName, function);

		String command = "this.projection(function(d, i) { return d3." + funcName + ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);

		if(result==null){
			return null;
		}

		return new Diagonal(engine, result);

	}

	/**
	 * Returns the current source accessor, which can either be a function or
	 * constant object. The default accessor is a function taking a JSO with a
	 * source attribute as described in <a href= "https:diagonal_source">d3 API
	 * reference</a>.
	 * 
	 * @return the current source accessor
	 */
	public JsObject source() {
		JsObject result = call("source");
		return result;
	}

	/**
	 * Returns the current target accessor, which can either be a function or
	 * constant object. The default accessor is a function taking a JSO with a
	 * target attribute as described in <a href="https:target">d3 API
	 * reference</a>.
	 * 
	 * @return the current target accessor
	 */
	public JsObject target() {
		JsObject result = call("target");
		return result;		
	}

	/**
	 * Sets the source accessor to be constant. Per the
	 * <a href= "https:diagonal_source">d3 API reference</a> the source
	 * describes a point of x, y coordinates, represented by the {@link Coords}
	 * class.
	 * 
	 * @param source
	 *            constant source
	 * @return the diagonal object
	 */
	public Diagonal source(Coords source) {
		JsObject result = call("source", source.getJsObject());
		return new Diagonal(engine, result);	
	}

	/**
	 * Sets the target accessor to be constant. Per the
	 * <a href= "https:diagonal_target">d3 API reference</a> the target
	 * describes a point of x, y coordinates, represented by the {@link Coords}
	 * class.
	 * 
	 * @param target
	 *            constant target
	 * @return the diagonal object
	 */
	public Diagonal target(Coords target) {
		JsObject result = call("target", target.getJsObject());
		return new Diagonal(engine, result);		
	}

	/**
	 * Sets the source accessor function. The default accessor function takes in
	 * a JSO with a source attribute and returns it. The source-accessor is
	 * invoked in the same manner as other value functions in D3. The function
	 * is passed two arguments, the current datum (d) and the current index (i).
	 * It is also possible to specify the source-accessor as a constant
	 * {@link #source(Coords)} rather than a function.
	 * 
	 * @param the
	 *            source accessor function
	 * @return the diagonal object
	 */
	public Diagonal source(DataFunction<?> function) {
		
		assertObjectIsNotAnonymous(function);

		String funcName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(funcName, function);

		String command = "this.source(function(d, i) { return d3." + funcName + ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);

		

		return new Diagonal(engine, result);
		
		
	}

	/**
	 * Sets the target accessor function. The default accessor function takes in
	 * a JSO with a target attribute and returns it. The target-accessor is
	 * invoked in the same manner as other value functions in D3. The function
	 * is passed two arguments, the current datum (d) and the current index (i).
	 * It is also possible to specify the target-accessor as a constant
	 * {@link #target(Coords)} rather than a function.
	 * 
	 * @param the
	 *            target accessor function
	 * @return the diagonal object
	 */
	public Diagonal target(DataFunction<?> function) {
		
		assertObjectIsNotAnonymous(function);

		String funcName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(funcName, function);

		String command = "this.target(function(d, i) { return d3." + funcName + ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);

		

		return new Diagonal(engine, result);
	}

	//#end region
}
