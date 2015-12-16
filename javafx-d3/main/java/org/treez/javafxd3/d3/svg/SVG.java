package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Provide access to svg routines.
 */
public class SVG extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public SVG(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
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
		JSObject result = call("arc");
		return new Arc(webEngine, result);
	}

	/**
	 * Create a new default {@link Area} generator.
	 * 
	 * @return the area
	 */
	public Area area() {
		JSObject result = call("area");
		return new Area(webEngine, result);
	}

	/**
	 * Create a new default {@link Axis} generator.
	 * 
	 * @return the axis
	 */
	public Axis axis() {
		JSObject result = call("axis");
		return new Axis(webEngine, result);
	}

	/**
	 * Create a new default {@link Brush} generator.
	 * 
	 * @return the brush
	 */
	public Brush brush() {
		JSObject result = call("brush");
		return new Brush(webEngine, result);
	}

	/**
	 * Create a new default {@link Chord} generator.
	 * 
	 * @return the chord
	 */
	public Chord chord() {
		JSObject result = call("chord");
		return new Chord(webEngine, result);
	}

	/**
	 * Create a new default {@link Diagonal} generator.
	 * 
	 * @return the diagonal
	 */
	public Diagonal diagonal() {
		JSObject result = call("diagonal");
		return new Diagonal(webEngine, result);
	}

	/**
	 * Create a new default {@link Line} generator.
	 * 
	 * @return the line
	 */
	public Line line() {
		JSObject result = call("line");
		return new Line(webEngine, result);
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
		JSObject result = evalForJsObject(command);
		return new RadialLine(webEngine, result);
	}

	/**
	 * Create a new default {@link Symbol} generator.
	 * 
	 * @return the symbol
	 */
	public Symbol symbol() {
		JSObject result = call("symbol");
		return new Symbol(webEngine, result);
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
