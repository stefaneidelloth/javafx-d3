package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.behaviour.Drag.DragEventType;
import org.treez.javafxd3.d3.functions.DataFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * A {@link PathDataGenerator} generating symbols shapes.
 * <p>
 * While the default accessors generate static symbols, it is common to set one
 * or more of the accessors using a function, such as setting the size
 * proportional to a dimension of data for a scatterplot. The returned function
 * generates path data for various symbols (see {@link SymbolType} for all the
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
	 * @param engine
	 * @param wrappedJsObject
	 *
	 */
	public Symbol(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	

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
	public Symbol type(SymbolType type) {

		String value = type.getValue();
		String command = "this.type('" + value + "');";
		JsObject result = evalForJsObject(command);
		return new Symbol(engine, result);
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
	public Symbol type(DataFunction<SymbolType> typeAccessorFunction) {

		assertObjectIsNotAnonymous(typeAccessorFunction);

		JsObject d3JsObject = getD3();
		String accessorName = createNewTemporaryInstanceName();

		d3JsObject.setMember(accessorName, typeAccessorFunction);

		String command = "this.type(function(d, i) { " //
				+ "var t = d3." + accessorName + ".apply(this,d,i);" //
				+ " return t.getValue();" //
				+ " });";
		JsObject result = evalForJsObject(command);
		if(result==null){
			return null;
		}
		return new Symbol(engine, result);

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
		JsObject result = call("size", sizeInSquarePixels);
		return new Symbol(engine, result);
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
	public Symbol size(DataFunction<Integer> sizeAccessorFunction) {

		assertObjectIsNotAnonymous(sizeAccessorFunction);

		JsObject d3JsObject = getD3();
		String accessorName = createNewTemporaryInstanceName();

		d3JsObject.setMember(accessorName, sizeAccessorFunction);

		String command = "this.size(function(d, i) {" //
				+ "return d3." + accessorName + ".apply(this,d,i);" //
				+ " });";
		JsObject result = evalForJsObject(command);
		
		d3JsObject.removeMember(accessorName);
		if(result==null){
			return null;
		}
		
		return new Symbol(engine, result);

	}
	
	/**
	 * Generate the path data as String.	
	 * @param data
	 *            an array of data
	 * @return the generated path data
	 */
	public String generate() {			
		String command = "this()";		
		String result = (String) eval(command);		
		return result;		
	}

}
