package com.github.javafxd3.d3.svg;

import java.util.Arrays;
import java.util.List;

import com.github.javafxd3.d3.behaviour.Drag.DragEventType;
import com.github.javafxd3.d3.functions.DatumFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * A {@link PathDataGenerator} generating symbols shapes.
 * <p>
 * While the default accessors generate static symbols, it is common to set one
 * or more of the accessors using a function, such as setting the size
 * proportional to a dimension of data for a scatterplot. The returned function
 * generates path data for various symbols (see {@link Type} for all the
 * symbols).
 * <p>
 * Note that the symbol does not include accessors for x and y. Instead, you can
 * use the path element's transform attribute to position the symbols, as in:
 *
 * <pre>
 * {@code
 * vis.selectAll("path")
 * .data(data)
 * .enter().append("path")
 * .attr("transform", function(d) {
 * 		return "translate(" + x(d.x) + "," + y(d.y) + ")";
 * })
 * .attr("d", d3.svg.symbol());
 *
 * </pre>
 *
 * In the future, we may add x- and y-accessors for parity with the line and
 * area generators. The symbol will be centered at the origin (0,0) of the local
 * coordinate system. You can also use SVG's built-in basic shapes to produce
 * many of these symbol types, though D3's symbol generator is useful in
 * conjunction with path elements because you can easily change the symbol type
 * and size as a function of data.
 */
public class Symbol extends PathDataGenerator {

	//#region CONSTRUCTORS

	/**
	 * Constructs a new symbol generator with the default type- and
	 * size-accessor functions (that make no assumptions about input data, and
	 * produce a circle sized 64 square pixels).
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 *
	 */
	public Symbol(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	/**
	 * Shape of the symbol.
	 * <p>
	 * Types are normalized to have the same area in square pixels, according to
	 * the specified size. However, note that different types' sizes may be
	 * affected by the stroke and stroke width in different ways. All of the
	 * types are designed to be visible when only a fill style is used (unlike
	 * the Protovis cross), although they generally look better when both a fill
	 * and stroke is used.
	 */
	public static enum Type {
		/**
		 * A circle
		 */
		CIRCLE("circle"),
		/**
		 * A greek cross or plus sign
		 */
		CROSS("cross"),
		/**
		 * A rhombus
		 */
		DIAMOND("diamond"),
		/**
		 * An axis-aligned square.
		 */
		SQUARE("square"),
		/**
		 * A downward-pointing equilateral triangle.
		 */
		TRIANGLE_DOWN("triangle-down"),
		/**
		 * a upward-pointing equilateral triangle.
		 */
		TRIANGLE_UP("triangle-up");

		private final String value;

		private Type(final String value) {
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		public static List<String> jsValues() {
			return Arrays.asList(CIRCLE.value, CROSS.value, DIAMOND.value, SQUARE.value, TRIANGLE_DOWN.value,
					TRIANGLE_UP.value);
		}
	}

	/**
	 * Set the type of the symbol using the specified {@link DragEventType}
	 * constant.
	 * <p>
	 * The default type is {@link DragEventType#CIRCLE}.
	 *
	 *
	 *
	 * @param type
	 * @return
	 */
	public Symbol type(Type type) {

		String value = type.getValue();
		String command = "this.type('" + value + "');";
		JSObject result = evalForJsObject(command);
		return new Symbol(webEngine, result);
	}

	/**
	 * Set the type of the symbol using the specified function returning a
	 * DragEventType.
	 * <p>
	 *
	 * @param typeAccessorFunction
	 *            the function that return the {@link DragEventType} of symbol.
	 * @return this instance for chaining
	 */
	public Symbol type(DatumFunction<Type> typeAccessorFunction) {

		assertObjectIsNotAnonymous(typeAccessorFunction);

		JSObject d3JsObject = getD3();
		String accessorName = createNewTemporaryInstanceName();

		d3JsObject.setMember(accessorName, typeAccessorFunction);

		String command = "this.type(function(d, i) { " //
				+ "var t = d3." + accessorName + ".apply(this,{datum:d},i);" //
				+ " return t.getValue();" //
				+ " });";
		JSObject result = evalForJsObject(command);
		return new Symbol(webEngine, result);

	}

	/**
	 * Set the size of the symbols.
	 * <p>
	 * The default size is 64.
	 *
	 * @param sizeInSquarePixels
	 *            the size in square pixels
	 * @return this instance for chaining
	 */
	public Symbol size(int sizeInSquarePixels) {
		JSObject result = call("size", sizeInSquarePixels);
		return new Symbol(webEngine, result);
	}

	/**
	 * Set the size of the symbols using the specified function returning an
	 * integer.
	 * <p>
	 *
	 * @param sizeAccessorFunction
	 *            the function that return the {@link DragEventType} of symbol.
	 * @return this instance for chaining
	 */
	public Symbol size(DatumFunction<Integer> sizeAccessorFunction) {

		assertObjectIsNotAnonymous(sizeAccessorFunction);

		JSObject d3JsObject = getD3();
		String accessorName = createNewTemporaryInstanceName();

		d3JsObject.setMember(accessorName, sizeAccessorFunction);

		String command = "this.size(function(d, i) {" //
				+ "return d3." + accessorName + ".apply(this,{datum:d},i);" //
				+ " });";
		JSObject result = evalForJsObject(command);
		return new Symbol(webEngine, result);

	}

}
