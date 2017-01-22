package org.treez.javafxd3.functionplot;

import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

@SuppressWarnings("javadoc")
public class Options extends JavaScriptObject {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	public Options(JsEngine engine) {
		super(engine);

		String command = "var options = {" //
				+ "width: 200," //
				+ "height: 200," + "target: '#root', " //				
				+ "title: null, " //
				+ "xDomain: [-7,7], " //
				//+ "yDomain: [0,1], " //
				+ "xLabel: '', " //
				+ "yLabel: '', " //
				+ "disableZoom: true, " //
				+ "grid: true, " //
				+ "tip: null, " //
				+ "annotations: null, " //
				+ "data: null, " //
				+ "plugins: null" //
				+ "};";
		engine.executeScript(command);
		JsObject options = (JsObject) engine.executeScript("options");
		setJsObject(options);
	}

	public Options(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	//#end region

	//#region ACCESSORS

	//#region WIDTH

	public Double getWidth() {
		Double result = getMemberForDouble("width");
		return result;
	}

	public Options setWidth(Double width) {
		setMember("width", width);
		return this;
	}

	//#end region

	//#region HEIGHT

	public Double getHeight() {
		Double result = getMemberForDouble("height");
		return result;
	}

	public Options setHeight(Double height) {
		setMember("height", height);
		return this;
	}

	//#end region

	//#region TARGET

	public String getTarget() {
		String result = getMemberForString("target");
		return result;
	}

	public Options setTarget(String target) {
		setMember("target", target);
		return this;
	}

	//#end region

	//#region TITLE

	public String getTitle() {
		String result = getMemberForString("title");
		return result;
	}

	public Options setTitle(String title) {
		setMember("title", title);
		return this;
	}

	//#end region

	//#region X LABEL

	//#end region

	public String getXLabel() {
		String result = getMemberForString("xLabel");
		return result;
	}

	public Options setXLabel(String xLabel) {
		setMember("xLabel", xLabel);
		return this;
	}

	//#end region

	//#region Y LABEL

	public String getYLabel() {
		String result = getMemberForString("xLabel");
		return result;
	}

	public Options setYLabel(String yLabel) {
		setMember("yLabel", yLabel);
		return this;
	}

	//#end region

	//#region X DOMAIN

	public void setXDomain(double xMin, double xMax) {
		String command = "this.xDomain = [" + xMin + "," + xMax + "]";
		eval(command);
	}

	//#end region

	//#region Y DOMAIN

	public void setYDomain(double yMin, double yMax) {
		String command = "this.yDomain = [" + yMin + "," + yMax + "]";
		eval(command);
	}

	//#end region

	//#region ZOOM

	public Options enableZoom() {
		setMember("disableZoom", false);
		return this;
	}

	public Options disableZoom() {
		setMember("disableZoom", true);
		return this;
	}

	public boolean isEnabledZoom() {
		boolean isDisabled = getMemberForBoolean("disableZoom");
		return !isDisabled;
	}

	//#end region

	//#region GRID

	public Options enableGrid() {
		setMember("grid", true);
		return this;
	}

	public Options disableGrid() {
		setMember("grid", false);
		return this;
	}

	public boolean isEnabledGrid() {
		boolean isGrid = getMemberForBoolean("grid");
		return isGrid;
	}

	//#end region

	//#region DATA

	public Options setDataExpression(String dataExpression) {
		JsObject data = evalForJsObject(dataExpression);
		setMember("data", data);
		return this;
	}

	//#end region

	//#end region

}
