package org.treez.javafxd3.d3.geo;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

/**
 * 
 */
public class Geography extends JavaScriptObject {

	//#region CONSTRUCTORS

	/**
	 * @param engine
	 * @param wrappedJsObject
	 */
	public Geography(JsEngine engine, JsObject wrappedJsObject) {
		super(engine);
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
		JsObject result = call("albers");
		return new ConicProjection(engine, result);
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
		JsObject result = call("conicEqualArea");
		return new ConicProjection(engine, result);
	}

	//#end region
}
