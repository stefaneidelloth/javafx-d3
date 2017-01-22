package org.treez.javafxd3.d3.geom;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ArrayUtils;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.layout.Link;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * Voronoi layouts are particularly useful for invisible interactive regions, as
 * demonstrated in Nate Vack’s <a
 * href="http://bl.ocks.org/njvack/1405439">Voronoi picking</a> example. See
 * Tovi Grossman’s paper on <a
 * href="http://www.tovigrossman.com/BubbleCursor">bubble cursors</a> for a
 * related concept.
 */
public class Voronoi extends JavaScriptObject {

//#region CONSTRUCTORS
	
    /**
     * @param engine
     * @param wrappedJsObject
     */
    public Voronoi(JsEngine engine, JsObject wrappedJsObject) {
    	super(engine);
    	setJsObject(wrappedJsObject);
    }
    
    //#end region
    
    //#region METHODS

    /**
     * Sets the clip extent of the Voronoi layout to the specified bounds and
     * returns the layout.
     * <p>
     * Use of a clip extent is strongly recommended, as unclipped polygons may
     * have large coordinates which do not display correctly.
     * <p>
     * Alternatively, you can also employ custom clipping without specifying a
     * size, either in SVG or by post-processing with
     *  Polygon#clip(Array).
     * <p>
     * To clear the clipping, see {@link #clearClipExtent()}
     * <p>
     *
     * @param x0
     *            the left side of the extent
     * @param y0
     *            the top side of the extent
     * @param x1
     *            the right side of the extent
     * @param y1
     *            the bottom side of the extent
     * @return the current layout
     */
    public  Voronoi clipExtent(int x0, int y0, int x1, int y1){
    	String command = "this.clipExtent([ [ "+x0+", "+y0+" ], [ "+x1+", "+y1+" ] ]);";
    	JsObject result = evalForJsObject(command);
    	return new Voronoi(engine, result);
    }

    /**
     * Clear the extent clipping.
     * <p>
     *
     * @return
     */
    public  Voronoi clearClipExtent(){
    	Object arg = null;
    	JsObject result = call("clipExtent", arg);
    	return new Voronoi(engine, result);		
    }

    /**
     * Get the current clip extent which defaults to null.
     * <p>
     *
     * @return the current clip extent which defaults to null.
     */
    public  Array<Array<Double>> clipExtent(){
    	JsObject result = call("clipExtendt");
    	return new Array<Array<Double>>(engine, result);    	
    }

    /**
     * Returns an array of polygons, one for each input vertex in the specified
     * data array.
     * <p>
     * If any vertices are coincident or have NaN positions, the behavior of
     * this method is undefined: most likely, invalid polygons will be returned!
     * You should filter invalid vertices, and consolidate coincident vertices,
     * before computing the tessellation.
     * <p>
     *
     * @param vertices
     *            the array of vertices
     * @return the array of polygons
     */
    public  <T> Array<T> apply(Array<T> vertices){
    	JsObject arrayObj = vertices.getJsObject();
    	JsObject result = callThisForJsObject(arrayObj);
    	return new Array<T>(engine, result);    	
    }
    
    public  Array<Double> apply(Double[][] vertices){
    	String arrayString = ArrayUtils.createArrayString(vertices);
    	String command = "this(" + arrayString + ")";
    	JsObject result = evalForJsObject(command);
    	return new Array<Double>(engine, result);    	
    }

    /**
     * Sets the x-coordinate accessor.
     * <p>
     * The default accessor consider datum as a two element array and returns
     * the first element.
     *
     * @param xAccessor
     *            the x accessor
     * @return the current layout
     */
    public  Voronoi x(DataFunction<Double> xAccessor){
    	
    	assertObjectIsNotAnonymous(xAccessor);

		String funcName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(funcName, xAccessor);

		String command = "this.x(function(d, i) { return d3." + funcName + ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);

		d3JsObject.removeMember(funcName);

		if(result==null){
			return null;
		}
		return new Voronoi(engine, result);   	
    	
    }

    /**
     * Sets the y-coordinate accessor.
     * <p>
     * The default accessor consider datum as a two element array and returns
     * the first element.
     *
     * @param yAccessor
     *            the y accessor
     * @return the current layout
     */

    public  Voronoi y(DataFunction<Double> yAccessor){
    	
    	assertObjectIsNotAnonymous(yAccessor);

		String funcName = createNewTemporaryInstanceName();
		JsObject d3JsObject = getD3();
		d3JsObject.setMember(funcName, yAccessor);

		String command = "this.y(function(d, i) { return d3." + funcName + ".apply(this,d,i); });";
		JsObject result = evalForJsObject(command);

		d3JsObject.removeMember(funcName);

		if(result==null){
			return null;
		}
		return new Voronoi(engine, result); 
    }

    /**
     * TODO: make a demo from http://bl.ocks.org/mbostock/1073373 TODO:
     * documentation
     * @param nodes 
     *
     * @return
     * @experimental
     */
    public  Array<Link> links(Object[] nodes){
    	
    	JsObject result = call("links");
    	if(result==null){
    		return null;
    	}
    	return new Array<>(engine, result);
    	
    }

    /**
     * TODO: make a demo from http://bl.ocks.org/mbostock/1073373 TODO:
     * documentation
     * @param nodes 
     *
     * @return
     * @experimental
     */
    public  Array<Link> triangles(Object[] nodes){
    	JsObject result = call("triangles");
    	if(result==null){
    		return null;
    	}
    	return new Array<>(engine, result);
    }

}
