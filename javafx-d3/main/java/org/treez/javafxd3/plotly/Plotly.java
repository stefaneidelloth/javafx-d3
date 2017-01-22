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

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;


public class Plotly extends JavaScriptObject {

	//#region ATTRIBUTES

	

	//#end region

	//#region CONSTRUCTORS

	public Plotly(JsEngine engine) {
		super(engine);
		JsObject plotly = (JsObject) engine.executeScript("Plotly");
		setJsObject(plotly);
	}

	//#end region

	//#region METHODS	
	
	public PlotResult newPlot(String targetDiv, Array<Data> data, Layout layout, Configuration configuration){
		JsObject dataObj = data.getJsObject();
	
		JsObject layoutObj = layout.getJsObject();
		JsObject configurationObj = configuration.getJsObject();		
		JsObject result = call("newPlot", targetDiv, dataObj, layoutObj, configurationObj);
		return new PlotResult(engine, result);		
	}		
	
	public void restyle(Element element, Data data, int... indices ){
		JsObject elementObj = element.getJsObject();
		JsObject dataObj = data.getJsObject();
		call("restyle", elementObj, dataObj, indices);
	}
	
	public void relayout(Element element, Layout data, int... indices ){
		JsObject elementObj = element.getJsObject();
		JsObject layoutObj = data.getJsObject();
		call("relayout", elementObj, layoutObj, indices);
	}
	
	public void addTraces(Element element, Data data){
		JsObject elementObj = element.getJsObject();
		JsObject dataObj = data.getJsObject();
		call("addTraces", elementObj, dataObj);
	}
	
	public void deleteTraces(Element element, int indice){
		throw new IllegalStateException("Not yet implemented");
	}
	
	public void moveTraces(Element element, int indice){
		throw new IllegalStateException("Not yet implemented");
	}
	
	public void redraw(Element element){
		JsObject elementObj = element.getJsObject();
		call("redraw", elementObj);		
	}
	
	//#region CREATE PLOTLY OBJECTS
	
		/**
		 * Creates a Data array with a single entry
		 */
		public SingleData createSingleData() {		
			return new SingleData(engine);
		}
		
		public Data createData() {		
			return new Data(engine);
		}

		public Layout createLayout() {
			return new Layout(engine);
		}

		public Configuration createConfiguration() {
			return new Configuration(engine);
		}
		
		public Contours createContourOptions() {
			return new Contours(engine);
		}
		
		public Axis createAxis() {
			return new Axis(engine);
		}
		
		public Margin createMargin() {
			return new Margin(engine);
		}
		
		public ZeroMargin createZeroMargin() {
			return new ZeroMargin(engine);
		}
		
		public Line createLine() {
			return new Line(engine);
		}
		
		public ColorBar createColorBar() {
			return new ColorBar(engine);
		}
		
		public Font createFont() {
			return new Font(engine);
		}
		
		//#end region
	
	//#region RETRIEVE SELECTIONS / NODES
	
	public Selection getSvg() {
		D3 d3 = new D3(engine);
		Selection svgSelection = d3.select(".main-svg");
		return svgSelection;
	}
	
	public Selection getContour() {
		D3 d3 = new D3(engine);
		Selection contourSelection = d3.select(".contour");
		return contourSelection;
	}
	
	public Element getElementById(String elementId){
		JsObject result = evalForJsObject("document.getElementById('" + elementId + "');");
		return new Element(engine, result);		
	}

	

	
	
	//#end region

		

	//#end region

}
