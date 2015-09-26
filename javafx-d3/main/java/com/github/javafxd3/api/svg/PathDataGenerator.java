package com.github.javafxd3.api.svg;


import com.github.javafxd3.api.arrays.ArrayUtils;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.IsFunction;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


/**
 *
 * A {@link PathDataGenerator} is an object containing information to simplify
 * the construction of the <code>d</code> attribute for SVG <code>path</code> element, allowing users to generate
 * complex shapes.
 * <p>
 * The <code>d<language of path
 * commands, such as moveto (M), lineto (L) and closepath (Z). {@link PathDataGenerator}s are Javascript functions that
 * generate these commands. <br>
 * From a Javascript point of view, a {@link PathDataGenerator} is both an object containing properties and a function
 * that can be called to generate the path.
 * <p>
 * When passing the generator to the <code>d</code> attribute of a <code>path</code> selection, the function represented
 * by the generator is called for each datum of the selection data. The function takes the datum in argument and return
 * the path.
 * <p>
 * Each generator specifies a default way of using the datum to create the path, but generally speaking, the default
 * behaviour can be overriden by providing {@link DatumFunction} for each generator attribute. Please refer to generator
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
 *
 * 
 *
 */
public abstract class PathDataGenerator extends JavaScriptObject implements IsFunction {

//#region CONSTRUCTORS
	
    /**
     * Constructor
     * @param webEngine
     * @param wrappedJsObject
     */
    public PathDataGenerator(WebEngine webEngine, JSObject wrappedJsObject) {
    	super(webEngine);
    	setJsObject(wrappedJsObject);
    }
    
    //#end region
    
    //#region METHODS

    /**
     * Generate the path data as String using the given data object in argument.
     * <p>
     * The data object must contains valid attributes for the corresponding generator to work (see each subclass
     * documentation).
     * <p>
     * 
     * 
     * @param data
     *            an array of data
     * @return the generated path data
     */
    public  String generate(Object data) {
    	String memberName = "temp__data";
    	JSObject jsObj = getJsObject();
    	jsObj.setMember(memberName, data);
    	String command = "this(this."+memberName+")";    	   	
    	String result = evalForString(command);
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
    public final String generate(final Object[] data) {
    	
    	    	
    	String totalResult = "";
    	
    	for(Object jsObj:data){
    		String singleResult = generate(jsObj);
    		totalResult = totalResult + " " + singleResult;    		
    	}
    	  	    	
    	return totalResult.trim();	
    	    	
    }

    /**
     * Alias for #generate(JavaScriptObject).
     * 
     * @param data
     *            an array of data
     * @return the generated path data
     */
    public final String generate(final double... data) {
    	String arrayString = ArrayUtils.createArrayString(data);
    	String command = "this(" + arrayString + ");";
    	String result = evalForString(command);
    	return result;
    }

    /**
     * Generate the path data as String using the given data object in argument.
     * <p>
     * The data object must contains valid attributes for the corresponding generator to work (see each subclass
     * documentation).
     * <p>
     * 
     * Apply the function using the given object in argument.
     * <p>
     * The object argument must provide missing attributes expected by the path generator. For instance, if this path
     * generator is a {@link Line}, the provided object could be an array of {x,y} objects. Please refer to subclass
     * documentation for more information.
     * 
     * 
     * @param data
     *            the array of data
     * @param index
     *            an index to be passed to each accessor functions
     * @return the generated path data
     */
    public  String generate(JSObject data, int index) {    	    	
    	String memberName = "temp__data";
    	JSObject jsObj = getJsObject();
    	jsObj.setMember(memberName, data);
    	String command = "this(this."+memberName+","+index+")";    	   	
    	String result = evalForString(command);
    	jsObj.removeMember(memberName);    	
    	return result;	    	
    }
    
  //#end region
}
