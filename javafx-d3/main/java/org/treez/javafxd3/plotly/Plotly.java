package org.treez.javafxd3.plotly;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;
import org.treez.javafxd3.plotly.configuration.Configuration;
import org.treez.javafxd3.plotly.data.Data;
import org.treez.javafxd3.plotly.data.Font;
import org.treez.javafxd3.plotly.data.SingleData;
import org.treez.javafxd3.plotly.data.contour.Contours;
import org.treez.javafxd3.plotly.data.contour.colorbar.ColorBar;
import org.treez.javafxd3.plotly.data.line.Line;
import org.treez.javafxd3.plotly.layout.Axis;
import org.treez.javafxd3.plotly.layout.Layout;
import org.treez.javafxd3.plotly.layout.margin.Margin;
import org.treez.javafxd3.plotly.layout.margin.ZeroMargin;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;


public class Plotly extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Plotly(WebEngine webEngine) {
		super(webEngine);
		JSObject plotly = (JSObject) webEngine.executeScript("Plotly");
		setJsObject(plotly);
	}

	//#end region

	//#region METHODS	
	
	public PlotResult newPlot(String targetDiv, Array<Data> data, Layout layout, Configuration configuration){
		JSObject dataObj = data.getJsObject();
	
		JSObject layoutObj = layout.getJsObject();
		JSObject configurationObj = configuration.getJsObject();		
		JSObject result = call("newPlot", targetDiv, dataObj, layoutObj, configurationObj);
		return new PlotResult(webEngine, result);		
	}		
	
	public void restyle(Element element, Data data, int... indices ){
		JSObject elementObj = element.getJsObject();
		JSObject dataObj = data.getJsObject();
		call("restyle", elementObj, dataObj, indices);
	}
	
	public void relayout(Element element, Layout data, int... indices ){
		JSObject elementObj = element.getJsObject();
		JSObject layoutObj = data.getJsObject();
		call("relayout", elementObj, layoutObj, indices);
	}
	
	public void addTraces(Element element, Data data){
		JSObject elementObj = element.getJsObject();
		JSObject dataObj = data.getJsObject();
		call("addTraces", elementObj, dataObj);
	}
	
	public void deleteTraces(Element element, int indice){
		throw new IllegalStateException("Not yet implemented");
	}
	
	public void moveTraces(Element element, int indice){
		throw new IllegalStateException("Not yet implemented");
	}
	
	public void redraw(Element element){
		JSObject elementObj = element.getJsObject();
		call("redraw", elementObj);		
	}
	
	//#region CREATE PLOTLY OBJECTS
	
		/**
		 * Creates a Data array with a single entry
		 */
		public SingleData createSingleData() {		
			return new SingleData(webEngine);
		}
		
		public Data createData() {		
			return new Data(webEngine);
		}

		public Layout createLayout() {
			return new Layout(webEngine);
		}

		public Configuration createConfiguration() {
			return new Configuration(webEngine);
		}
		
		public Contours createContourOptions() {
			return new Contours(webEngine);
		}
		
		public Axis createAxis() {
			return new Axis(webEngine);
		}
		
		public Margin createMargin() {
			return new Margin(webEngine);
		}
		
		public ZeroMargin createZeroMargin() {
			return new ZeroMargin(webEngine);
		}
		
		public Line createLine() {
			return new Line(webEngine);
		}
		
		public ColorBar createColorBar() {
			return new ColorBar(webEngine);
		}
		
		public Font createFont() {
			return new Font(webEngine);
		}
		
		//#end region
	
	//#region RETRIEVE SELECTIONS / NODES
	
	public Selection getSvg() {
		D3 d3 = new D3(webEngine);
		Selection svgSelection = d3.select(".main-svg");
		return svgSelection;
	}
	
	public Selection getContour() {
		D3 d3 = new D3(webEngine);
		Selection contourSelection = d3.select(".contour");
		return contourSelection;
	}
	
	public Element getElementById(String elementId){
		JSObject result = evalForJsObject("document.getElementById('" + elementId + "');");
		return new Element(webEngine, result);		
	}

	

	
	
	//#end region

		

	//#end region

}
