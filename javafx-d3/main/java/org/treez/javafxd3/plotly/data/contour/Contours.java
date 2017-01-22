package org.treez.javafxd3.plotly.data.contour;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Contours extends JavaScriptObject {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	public Contours(JsEngine engine, JsObject jsObject) {
		super(engine, jsObject);
	}

	public Contours(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();
	}

	//#end region

	//#region ACCESSORS

	/**
	 * Sets the value of the first contour level
	 */
	public void setStart(double start) {
		setMember("start", start);
	}

	/**
	 * Sets the value of the last contour level.
	 */
	public void setEnd(double end) {
		setMember("end", end);
	}

	/**
	 * Sets the size of each contour level.
	 */
	public void setSize(double size) {
		setMember("size", size);
	}

	/**
	 * Toggle whether or not the contour lines appear on the plot.
	 */
	public void setShowLines(boolean showLines) {
		setMember("showLines", showLines);
	}

	/**
	 * Choose the coloring method for this contour trace. The default value is
	 * 'fill' where coloring is done evenly between each contour line. 'heatmap'
	 * colors on a grid point-by-grid point basis. 'lines' colors only the
	 * contour lines, each with respect to the color scale. 'none' prints all
	 * contour lines with the same color; choose their color in a Line object at
	 * the trace level if desired.
	 */
	public void setColoring(Coloring coloring) {
		setMember("coloring", coloring.toString());
	}
	
	public void setColoring(String coloring) {
		setMember("coloring", coloring);
	}

	//#end region

}
