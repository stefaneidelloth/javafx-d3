package org.treez.javafxd3.plotly.data;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.plotly.data.contour.ColorScale;
import org.treez.javafxd3.plotly.data.contour.Contours;
import org.treez.javafxd3.plotly.data.contour.colorbar.ColorBar;
import org.treez.javafxd3.plotly.data.line.Line;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class SingleData extends Array<Data> {

	//#region ATTRIBUTES

	Data data;

	//#end region

	//#region CONSTRUCTORS

	public SingleData(WebEngine webEngine, JSObject jsObject) {
		super(webEngine, jsObject);
	}

	public SingleData(WebEngine webEngine) {
		super(webEngine, null);

		String dummyName = createNewTemporaryInstanceName();
		webEngine.executeScript("var " + dummyName + "=[{}];");
		JSObject jsObject = (JSObject) webEngine.executeScript(dummyName);
		setJsObject(jsObject);
		JSObject firstEntry = evalForJsObject("this[0]");
		data = new Data(webEngine, firstEntry);
	}

	//#end region

	//#region METHODS	

	//#region TYPE

	public void setType(PlotlyType type) {
		data.setType(type);
	}

	public PlotlyType getType() {
		return data.getType();
	}

	//#end region

	//#region X

	public void setX(Double[] xData) {
		data.setX(xData);
	}
	
	public void setX(List<Double> xData) {
		data.setX(xData);
	}

	public void setX(Array<Double> xData) {
		data.setX(xData);
	}

	public Array<Double> getX() {
		return data.getX();
	}

	//#end region

	//#region Y

	public void setY(Double[] yData) {
		data.setY(yData);
	}
	
	public void setY(List<Double> yData) {
		data.setY(yData);
	}

	public void setY(Array<Double> yData) {
		data.setY(yData);
	}

	public Array<Double> getY() {
		return data.getY();
	}

	//#end region

	//#region Z

	public void setZ(Double[] zData) {
		data.setZ(zData);
	}
	
	public void setZ(Double[][] zData) {
		data.setZ(zData);
	}
	
	public void setZ(List<Double> zData) {
		data.setZ(zData);
	}

	public void setZ(Array<Double> yData) {
		data.setZ(yData);
	}

	public Array<Double> getZ() {
		return data.getZ();
	}
	
	public void setZAuto(boolean automatic) {
		data.setZAuto(automatic);		
	}

	public void setZMin(double zmin) {
		data.setZMin(zmin);		
	}

	public void setZMax(double zmax) {
		data.setZMax(zmax);		
	}

	//#end region

	//#region VALUES

	public void setValues(Double[] valueData) {
		data.setValues(valueData);
	}

	public void setValues(Array<Double> valueData) {
		data.setValues(valueData);
	}

	public Array<Double> getValues() {
		return data.getValues();
	}

	//#end region
	
	//#region TEXT
	
	public void setText(String[] textArray) {
		data.setText(textArray);		
	}	
	
	//#end region

	//#region LABELS

	public void setLabels(String[] labels) {
		data.setLabels(labels);
	}

	public void setLabels(Array<String> labels) {
		data.setLabels(labels);
	}

	public Array<String> getLabels() {
		return data.getLabels();
	}

	//#end region

	//#region MARKER

	public void setMarker(Marker marker) {
		data.setMarker(marker);
	}

	public Marker getMarker() {
		return data.getMarker();
	}	

	//#end region
	
	//#region CONTOURS
	
	public void setAutoContour(boolean autoContour) {
		data.setAutoContour(autoContour);		
	}
	
	public void setContours(Contours contours) {
		data.setContours(contours);		
	}	
	
	public void setNContours(int numberOfContours) {
		data.setNContours(numberOfContours);		
	}	
	
	public void setConnectGaps(boolean connectGaps) {
		data.setConnectGaps(connectGaps);		
	}
	
	//#end region
	
	//#region SCALE
	
	
	public void setShowScale(boolean showScale) {
		data.setShowScale(showScale);		
	}
	
	public void setColorScale(ColorScale scale) {
		data.setColorScale(scale);
	}	
	
	public void setColorScale(String scale) {
		data.setColorScale(scale);
	}	

	public void setReverseScale(boolean isReversedScale) {
		data.setReverseScale(isReversedScale);		
	}
	
	//#end region
	
	//#region COLORBAR
	
	public void setColorBar(ColorBar colorBar) {
		data.setColorBar(colorBar);		
	}
	
	//#end region
	
	//#region LINE	

	public void setLine(Line line) {
		data.setLine(line);		
	}
	
	//#end region
	
	//#region VISIBILITY & TRANSPARENCY	

	public void setOpacity(double opacity) {
		data.setOpacity(opacity);		
	}

	public void setVisible(boolean isVisible) {
		data.setVisible(isVisible);		
	}

	
	
	//#end region

	//#end region

}
