package com.github.javafxd3.api.svg;

import java.util.Arrays;
import java.util.List;

import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.IsFunction;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 *
 * A {@link PathDataGenerator} is an object containing information to simplify
 * the construction of the <code>d</code> attribute for SVG <code>path</code>
 * element, allowing users to generate complex shapes.
 * <p>
 * The <code>d<language of path
 * commands, such as moveto (M), lineto (L) and closepath (Z). {@link PathDataGenerator}s are Javascript functions that
 * generate these commands. <br>
 * From a Javascript point of view, a {@link PathDataGenerator} is both an object containing properties and a function
 * that can be called to generate the path.
 * <p>
 * When passing the generator to the <code>d</code> attribute of a
 * <code>path</code> selection, the function represented by the generator is
 * called for each datum of the selection data. The function takes the datum in
 * argument and return the path.
 * <p>
 * Each generator specifies a default way of using the datum to create the path,
 * but generally speaking, the default behaviour can be overriden by providing
 * {@link DatumFunction} for each generator attribute. Please refer to generator
 * subclass documention for more information.
 * <p>
 *
 * Usage:
 * <p>
 *
 * <pre>
 * {@code
 * PathDataGenerator pathGenerator = ...
 * Selection g = D3.select("body").append("svg").append("g");
 * g.append("path").attr("d", pathGenerator);
 *
 * }
 * </pre>
 * <p>
 *
 * @see <a href="https:generators">Official API</a>
 */
public abstract class PathDataGenerator extends JavaScriptObject implements IsFunction {

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public PathDataGenerator(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);
	}

	// #end region

	// #region METHODS

	/**
	 * Generate the path data as String using the given data object in argument.
	 * <p>
	 * The data object must contains valid attributes for the corresponding
	 * generator to work (see each subclass documentation).
	 * <p>
	 * 
	 * 
	 * @param data
	 *            an array of data
	 * @return the generated path data
	 */
	public String generate(JavaScriptObject data) {

		// get and store JSObject
		JSObject dataObj = data.getJsObject();
		String memberName = "temp__data";
		JSObject d3Obj = (JSObject) webEngine.executeScript("d3");
		d3Obj.setMember(memberName, dataObj);
		
		/*
		
		String functorCommand = "function d3_functor(v) {    return typeof v === \"function\" ? v : function() {      return v;    };  }";
		eval(functorCommand);
		
		JSObject o = (JSObject) eval("this");
		
		eval("var data = d3." + memberName +";");
		eval("var segments = [];");
		eval("var points = [];");
		eval("var i = -1;");
		eval("var n = data.length;");
		eval("var d;"); 
		eval("var fx = d3_functor(this.x);");
		eval("var fy = d3_functor(this.y);");    
		eval("var segment = function() { segments.push(\"M\", this.interpolate(.projection(points), this.tension)); }");    

	   String whileCommand = "while (++i < n) {   "+
	   "if (this.defined.call(this, d = data[i], i)) { "+     
	   "           points.push([ +fx.call(this, d, i), +fy.call(this, d, i) ]); "+
	   "      } else if (points.length) {      "+
	   "      segment();    "+    
	   "      points = []; "+     
	   "      }     "+
	   " }    ";
	   eval(whileCommand);

	    eval("if (points.length) segment();");  

	   Object result = eval("segments.length ? segments.join(\"\") : null;"); 
	   
	   */

		// execute command to generate data
		String command = "this([d3." + memberName + "])";
		
		String result = (String) eval(command);

		// delete temporary member
		d3Obj.removeMember(memberName);

		// return result
		return result;
	}

	/**
	 * Generate the path data as String using the given data object in argument.
	 * <p>
	 * The data object must contains valid attributes for the corresponding
	 * generator to work (see each subclass documentation).
	 * <p>
	 * 
	 * Apply the function using the given object in argument.
	 * <p>
	 * The object argument must provide missing attributes expected by the path
	 * generator. For instance, if this path generator is a {@link Line}, the
	 * provided object could be an array of {x,y} objects. Please refer to
	 * subclass documentation for more information.
	 * 
	 * 
	 * @param data
	 *            the array of data
	 * @param index
	 *            an index to be passed to each accessor functions
	 * @return the generated path data
	 */
	public String generate(JavaScriptObject data, int index) {

		// get and store JSObject
		JSObject dataObj = data.getJsObject();
		String memberName = "temp__data";
		JSObject jsObj = this.getJsObject();
		jsObj.setMember(memberName, dataObj);

		// execute command to generate data
		String command = "this(this." + memberName + ", " + index + ")";
		String result = evalForString(command);

		// delete temporary member
		jsObj.removeMember(memberName);

		return result;
	}

	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            the data
	 * @return generated path data
	 */
	public final String generate(final List<? extends JavaScriptObject> data) {
		return generate(Array.fromList(webEngine, data));
	}	
	
	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            the data
	 * @return generated path data
	 */
	public final String generate(final JavaScriptObject[] data) {		
		List<JavaScriptObject> list = Arrays.asList(data); 		
		return generate(Array.fromList(webEngine, list));
	}	
	
	/**
	 * Alias for #generate(JavaScriptObject).
	 * 
	 * @param data
	 *            an array of data
	 * @return the generated path data
	 */
	public final String generate(final Double... data) {
		Array<Double> array = Array.fromDoubles(webEngine, data);
		String result = generate(array);
		return result;
	}

	// #end region
}
