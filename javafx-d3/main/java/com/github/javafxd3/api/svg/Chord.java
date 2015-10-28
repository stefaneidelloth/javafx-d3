package com.github.javafxd3.api.svg;

import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Constructs a new chord generator with the default accessor functions (that
 * assume the input data is an object with named attributes matching the
 * accessors; see below for details).
 * <p>
 * While the default accessors assume that the chord dimensions are all
 * specified dynamically, it is very common to set one or more of the dimensions
 * as a constant, such as the radius. The returned function generates path data
 * for a closed shape connecting two arcs with quadratic Bézier curves, as in a
 * chord diagram:
 * 
 * <p>
 * <img src="https://github.com/mbostock/d3/wiki/chord.png">
 * <p>
 * 
 * A chord generator is often used in conjunction with an {@link Arc} generator,
 * so as to draw annular segments at the start and end of the chords. In
 * addition, the {@link com.github.javafxd3.api.layout.Chord} layout is useful
 * for generating objects that describe a set of grouped chords from a matrix,
 * compatible with the default accessors. 
 */
public class Chord extends PathDataGenerator {

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Chord(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);

	}

	// #end region

	// #region METHODS

	/**
	 * Set the source accessor.
	 * <p>
	 * The purpose of the source accessor is to return an object that describes
	 * the starting arc of the chord. The returned object is subsequently passed
	 * to the {@link #radius(DatumFunction)}, {@link #startAngle(DatumFunction)}
	 * and {@link #endAngle(DatumFunction)} accessors.
	 * <p>
	 * This allows these other accessors to be reused for both the source and
	 * target arc descriptions.
	 * <p>
	 * The default accessor assumes that the input data is a JavaScriptObject
	 * with suitably-named attributes.
	 * <p>
	 * The source-accessor is invoked in the same manner as other value
	 * functions in D3.
	 * <p>
	 * 
	 * @param accessor
	 *            the function returning the source arc object
	 * @return the current chord generator
	 */
	public Chord source(final DatumFunction<?> accessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .source(function(d, i) { return
		 * accessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 */
	}

	/**
	 * Set the target accessor.
	 * <p>
	 * The purpose of the target accessor is to return an object that describes
	 * the ending arc of the chord. The returned object is subsequently passed
	 * to the {@link #radius(DatumFunction)}, {@link #startAngle(DatumFunction)}
	 * and {@link #endAngle(DatumFunction)} accessors.
	 * <p>
	 * This allows these other accessors to be reused for both the source and
	 * target arc descriptions.
	 * <p>
	 * The default accessor assumes that the input data is a JavaScriptObject
	 * with suitably-named attributes.
	 * <p>
	 * The target-accessor is invoked in the same manner as other value
	 * functions in D3.
	 * <p>
	 * 
	 * @param accessor
	 *            the function returning the target arc object
	 * @return the current chord generator
	 */
	public Chord target(final DatumFunction<?> accessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .target(function(d, i) { return
		 * accessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 */
	}

	/**
	 * Set the radius accessor. The accessor will be invoked passing the source
	 * or target in the value parameter. The accessor must return the radius.
	 * <p>
	 * The default accessor assumes that the input source or target description
	 * is a JavaScriptObject with suitably-named attributes.
	 * <p>
	 * 
	 * @param accessor
	 *            the function returning the radius
	 * @return the current chord generator
	 */
	public Chord radius(final DatumFunction<Double> accessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .radius(function(d, i) { return
		 * accessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 */
	}

	/**
	 * Set the radius as a constant.
	 * <p>
	 * 
	 * @param radius
	 *            the radius
	 * @return the current chord generator
	 */
	public Chord radius(final double radius) {
		return this.radius(radius);
	}

	/**
	 * Set the start angle accessor. The accessor will be invoked passing the
	 * source or target in the value parameter. The accessor must return the
	 * start angle in radians.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * <p>
	 * The default accessor assumes that the input source or target description
	 * is a JavaScriptObject with suitably-named attributes.
	 * <p>
	 * 
	 * @param accessor
	 *            the function returning the start angle
	 * @return the current chord generator
	 */
	public Chord startAngle(final DatumFunction<Double> accessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .startAngle(function(d, i) { return
		 * accessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 */
	}

	/**
	 * Set the start angle as a constant in radians.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * <p>
	 * 
	 * @param startAngle
	 *            the angle in radians
	 * @return the current chord generator
	 */
	public Chord startAngle(final double startAngle) {
		return this.startAngle(startAngle);
	}

	/**
	 * Set the end angle accessor. The accessor will be invoked passing the
	 * source or target in the value parameter. The accessor must return the end
	 * angle in radians.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * <p>
	 * The default accessor assumes that the input source or target description
	 * is a JavaScriptObject with suitably-named attributes.
	 * <p>
	 * 
	 * @param accessor
	 *            the function returning the end angle
	 * @return the current chord generator
	 */
	public Chord endAngle(final DatumFunction<Double> accessor) {

		throw new IllegalStateException("not yet implemented");
		/*
		 * return this .endAngle(function(d, i) { return
		 * accessor.@com.github.gwtd3.api.functions.DatumFunction::apply(Lcom/
		 * google/gwt/dom/client/Element;Lcom/github/gwtd3/api/core/Value;I)(
		 * this,{datum:d},i); });
		 */
	}

	/**
	 * Set the end angle as a constant in radians.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees.
	 * <p>
	 * 
	 * @param endAngle
	 *            the angle in radians
	 * @return the current chord generator
	 */
	public Chord endAngle(final double endAngle) {
		return this.endAngle(endAngle);
	}
	
	//#end region
}
