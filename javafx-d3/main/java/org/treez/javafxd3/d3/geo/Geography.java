package org.treez.javafxd3.d3.geo;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * 
 */
public class Geography extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Geography(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine);
		setJsObject(wrappedJsObject);

	}

	//#end region

	//#region METHODS

	/**
	 * An alias for {@link #conicEqualArea()}, with USA-centric defaults:
	 * <ul>
	 * <li>scale 1000,
	 * <li>translate [480, 250],
	 * <li>rotation [96°, 0°],
	 * <li>center ⟨-0.6°, 38.7°⟩
	 * <li>parallels [29.5°, 45.5°],
	 * </ul>
	 * making it suitable for displaying the United States, centered around
	 * <a href="https://maps.google.com/maps?q=Hutchinson,+Kansas&z=5">
	 * Hutchinson, Kansas</a> in a 960×500 area.
	 * <p>
	 * The central meridian and parallels are specified by the
	 * <a href="http://www.usgs.gov/">USGS</a> in the 1970
	 * <a href="http://www.nationalatlas.gov/">National Atlas</a>.
	 * <p>
	 * 
	 * @return the projection
	 */
	public ConicProjection albers() {
		JSObject result = call("albers");
		return new ConicProjection(webEngine, result);
	}

	/**
	 * The Albers projection, as an
	 * <a href="http:
	 * area projection</a>, is recommended for
	 * <a href="http://mbostock.github.com/d3/ex/choropleth.html">choropleths
	 * </a> as it preserves the relative areas of geographic features.
	 * <p>
	 * 
	 * @return the projection
	 */
	public ConicProjection conicEqualArea() {
		JSObject result = call("conicEqualArea");
		return new ConicProjection(webEngine, result);
	}

	//#end region
}
