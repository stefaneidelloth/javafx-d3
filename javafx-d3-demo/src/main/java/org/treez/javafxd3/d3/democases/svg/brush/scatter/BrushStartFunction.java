package org.treez.javafxd3.d3.democases.svg.brush.scatter;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.application.Platform;

public class BrushStartFunction implements DataFunction<Void> {
	
	private ScatterPlotMatrixDemo scatterPlotMatrixDemo;
	private JsEngine engine;

	public BrushStartFunction(ScatterPlotMatrixDemo scatterPlotMatrixDemo, JsEngine engine){
		this.scatterPlotMatrixDemo=scatterPlotMatrixDemo;
		this.engine=engine;
	}
	
	@Override
	public Void apply(Object context, Object d, int index) {
		Platform.runLater(()->{			
			
			Element element = ConversionUtil.convertObjectTo(context, Element.class, engine);
			Point point = (Point) d;

			scatterPlotMatrixDemo.brushStart(element, point);
			
		});
		
		return null;
	}
}
