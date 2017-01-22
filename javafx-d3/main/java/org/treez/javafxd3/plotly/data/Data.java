package org.treez.javafxd3.plotly.data;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.plotly.data.contour.ColorScale;
import org.treez.javafxd3.plotly.data.contour.Contours;
import org.treez.javafxd3.plotly.data.contour.colorbar.ColorBar;
import org.treez.javafxd3.plotly.data.line.Line;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class Data extends JavaScriptObject {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	public Data(JsEngine engine, JsObject jsObject) {
		super(engine, jsObject);
	}

	public Data(JsEngine engine) {
		super(engine);
		setEmptyObjectAsJsObject();
	}

	//#end region

	//#region METHODS	

	//#region TYPE

	public void setType(PlotlyType type) {
		setMember("type", type.toString());
	}

	public PlotlyType getType() {
		String typeString = getMemberForString("type");
		return PlotlyType.fromString(typeString);
	}

	//#end region

	//#region X

	public void setX(Double[] xData) {
		Array<Double> array = Array.fromDoubles(engine, xData);
		setX(array);
	}

	public void setX(List<Double> xData) {
		Array<Double> array = Array.fromList(engine, xData);
		setX(array);
	}

	public void setX(Array<Double> xData) {
		JsObject arrayObj = xData.getJsObject();
		setMember("x", arrayObj);
	}

	public Array<Double> getX() {
		JsObject result = getMember("x");
		return new Array<Double>(engine, result);
	}

	//#end region

	//#region Y

	public void setY(Double[] yData) {
		Array<Double> array = Array.fromDoubles(engine, yData);
		setY(array);
	}

	public void setY(List<Double> yData) {
		Array<Double> array = Array.fromList(engine, yData);
		setY(array);
	}

	public void setY(Array<Double> yData) {
		JsObject arrayObj = yData.getJsObject();
		setMember("y", arrayObj);
	}

	public Array<Double> getY() {
		JsObject result = getMember("y");
		return new Array<Double>(engine, result);
	}

	//#end region

	//#region Z

	public void setZ(Double[] zData) {
		Array<Double> array = Array.fromDoubles(engine, zData);
		setZ(array);
	}

	public void setZ(Double[][] zData) {
		Array<Double> array = Array.fromDoubles(engine, zData);
		setZ(array);
	}

	public void setZ(List<Double> zData) {
		Array<Double> array = Array.fromList(engine, zData);
		setZ(array);
	}

	public void setZ(Array<Double> yData) {
		JsObject arrayObj = yData.getJsObject();
		setMember("z", arrayObj);
	}

	public Array<Double> getZ() {
		JsObject result = getMember("z");
		return new Array<Double>(engine, result);
	}

	public void setZAuto(boolean isZAuto) {
		setMember("zauto", isZAuto);
	}

	public void setZMin(double zmin) {
		setMember("zmin", zmin);
	}

	public void setZMax(double zmax) {
		setMember("zmax", zmax);
	}

	//#end region

	//#region VALUES

	public void setValues(Double[] valueData) {
		Array<Double> array = Array.fromDoubles(engine, valueData);
		setValues(array);
	}

	public void setValues(Array<Double> valueData) {
		JsObject arrayObj = valueData.getJsObject();
		setMember("values", arrayObj);
	}

	public Array<Double> getValues() {
		JsObject result = getMember("values");
		return new Array<Double>(engine, result);
	}

	//#end region

	//#region TEXT

	public void setText(String[] textArray) {
		setMember("text", textArray);
	}

	//#end region

	//#region LABELS

	public void setLabels(String[] labelData) {
		Array<String> array = Array.fromStrings(engine, labelData);
		setLabels(array);
	}

	public void setLabels(Array<String> labelData) {
		JsObject arrayObj = labelData.getJsObject();
		setMember("labels", arrayObj);
	}

	public Array<String> getLabels() {
		JsObject result = getMember("labels");
		return new Array<String>(engine, result);
	}

	//#end region

	//#region MARKER

	public void setMarker(Marker marker) {
		setMember("marker", marker.getJsObject());
	}

	public Marker getMarker() {
		JsObject result = getMember("marker");
		return new Marker(engine, result);
	}

	//#end region

	//#region CONTOURS

	public void setAutoContour(boolean autoContour) {
		setMember("autocontour", autoContour);
	}

	public void setContours(Contours contours) {
		setMember("contours", contours.getJsObject());
	}

	public void setNContours(int numberOfContours) {
		setMember("ncontours", numberOfContours);
	}

	public void setConnectGaps(boolean connectGaps) {
		setMember("connectgaps", connectGaps);
	}

	//#end region

	//#region COLOR BAR

	public void setColorBar(ColorBar colorBar) {
		setMember("colorbar", colorBar.getJsObject());
	}

	//#end region

	//#region SCALES

	public void setShowScale(boolean showScale) {
		setMember("showscale", showScale);
	}

	public void setColorScale(ColorScale scale) {
		setMember("colorscale", scale.toString());
	}

	public void setColorScale(String scale) {
		setMember("colorscale", scale);
	}

	public void setReverseScale(boolean isReversedScale) {
		setMember("reversescale", isReversedScale);
	}

	//#end region

	//#region LINE	

	public void setLine(Line line) {
		setMember("line", line.getJsObject());
	}

	//#end region

	//#region VISIBILITY & TRANSPARENCY	

	public void setOpacity(double opacity) {
		setMember("opacity", opacity);
	}

	public void setVisible(boolean isVisible) {
		setMember("visible", isVisible);
	}

	//#end region

	//#end region

}
