package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Provide access to svg routines.
 */
public class SVG extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param engine
	 * @param wrappedJsObject
	 */
	public SVG(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
		setJsObject(wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Create a new arc generator with default accessor functions. See
	 * {@link Arc} for details.
	 * 
	 * @return the arc
	 */
	public Arc arc() {
		JsObject result = call("arc");
		return new Arc(engine, result);
	}

	/**
	 * Create a new default {@link Area} generator.
	 * 
	 * @return the area
	 */
	public Area area() {
		JsObject result = call("area");
		return new Area(engine, result);
	}

	/**
	 * Create a new default {@link Axis} generator.
	 * 
	 * @return the axis
	 */
	public Axis axis() {
		JsObject result = call("axis");
		return new Axis(engine, result);
	}

	/**
	 * Create a new default {@link Brush} generator.
	 * 
	 * @return the brush
	 */
	public Brush brush() {
		JsObject result = call("brush");
		return new Brush(engine, result);
	}

	/**
	 * Create a new default {@link Chord} generator.
	 * 
	 * @return the chord
	 */
	public Chord chord() {
		JsObject result = call("chord");
		return new Chord(engine, result);
	}

	/**
	 * Create a new default {@link Diagonal} generator.
	 * 
	 * @return the diagonal
	 */
	public Diagonal diagonal() {
		JsObject result = call("diagonal");
		return new Diagonal(engine, result);
	}

	/**
	 * Create a new default {@link Line} generator.
	 * 
	 * @return the line
	 */
	public Line line() {
		JsObject result = call("line");
		return new Line(engine, result);
	}

	/**
	 * Constructs a new radial line generator with the default radius- and
	 * angle-accessor functions (that assume the input data is a two-element
	 * array of numbers; see below for details), and linear interpolation.
	 * <p>
	 * The returned function generates path data for an open piecewise linear
	 * curve, or polyline, as with the Cartesian line generator.
	 * <p>
	 * 
	 * @return the {@link RadialLine}.
	 */
	public RadialLine radialLine() {
		String command = "this.line.radial()";
		JsObject result = evalForJsObject(command);
		return new RadialLine(engine, result);
	}

	/**
	 * Create a new default {@link Symbol} generator.
	 * 
	 * @return the symbol
	 */
	public Symbol symbol() {
		JsObject result = call("symbol");
		return new Symbol(engine, result);
	}

	/**
	 * Create a new default {@link Diagonal} generator expressed in polar
	 * coordinates.
	 * 
	 * @return the diagonal
	 */
	public Diagonal radialDiagonal() {
		return this.diagonal();
	}

	//#end region
}
