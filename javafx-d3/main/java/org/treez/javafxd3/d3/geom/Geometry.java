package org.treez.javafxd3.d3.geom;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * The geometry module.
 */
public class Geometry extends JavaScriptObject {

	//#region CONSTRUCTORS
	
    /**
     * @param engine
     * @param wrappedJsObject
     */
    public Geometry(JsEngine engine, JsObject wrappedJsObject) {
    	super(engine);
    	setJsObject(wrappedJsObject);
    }
    
    //#end region
    
    //#region METHODS

    /**
     * Create a new hull layout with the default x- and y-accessors.
     * <p>
     *
     * @return the hull
     */
    public Hull hull(){
    	JsObject result = call("hull");
    	return new Hull(engine, result);		
    }

    /**
     * Compute the hull with default x- and y-accessors. See
     * Hull#apply(Array).
     * <p>
     * @param vertices 
     *
     * @return the convex hull as an array of vertices
     */
    public  <T> Array<T> hull(Array<T> vertices){    	
    	JsObject verticesObj = vertices.getJsObject();
    	JsObject result = call("hull", verticesObj);
    	return new Array<T>(engine, result);			
    }

    /**
     * Create a new hull layout with the default x- and y-accessors for the
     * given array of vertices. See {@link Hull#apply(List)}.
     * <p>
     * @param vertices 
     *
     * @return the convex hull as a list of vertices
     */
    public final <T> List<? extends T> hull(final List<T> vertices, Class<T> clazz) {
    	Array<T> array = Array.fromList(engine, vertices);    	
    	Array<T> hullArray = this.hull(array);
    	List<? extends T> hullList = hullArray.asList(clazz);
    	return hullList;
    }

    /**
     * Returns a polygon object, which is the array of vertices with additional
     * methods added to it.
     * <p>
     * @param vertices 
     *
     * @return the {@link Polygon} object
     */
    public Polygon polygon(Double[][] vertices){
    	String arrayString = ArrayUtils.createArrayString(vertices);
    	String command = "this.polygon(" + arrayString+ ")";
    	JsObject result = evalForJsObject(command);
    	return new Polygon(engine, result);			
    }

    /**
     * Creates a new quadtree factory with the default x-accessor and y-accessor
     * (that assume the input data is a two-element array of numbers; see below
     * for details) and extent.
     * <p>
     * The default extent is null, such that it will be computed automatically
     * from the input points.
     * <p>
     *
     * @return the {@link Quadtree} factory
     */
    public Quadtree quadtree(){
    	JsObject result = call("quadtree");
    	return new Quadtree(engine, result);		
    }

    /**
     * Creates a Voronoi layout with default accessors.
     *
     * @return the Voronoi factory
     */
    public Voronoi voronoi(){
    	JsObject result = call("voronoi");
    	return new Voronoi(engine, result);			
    }
    
    //#end region

}
