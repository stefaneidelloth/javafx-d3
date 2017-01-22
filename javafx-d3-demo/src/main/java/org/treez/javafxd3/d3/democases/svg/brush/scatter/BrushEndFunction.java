package org.treez.javafxd3.d3.democases.svg.brush.scatter;

import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.application.Platform;

public class BrushEndFunction implements DataFunction<Void> {

	private ScatterPlotMatrixDemo scatterPlotMatrixDemo;

	public BrushEndFunction(ScatterPlotMatrixDemo scatterPlotMatrixDemo) {
		this.scatterPlotMatrixDemo = scatterPlotMatrixDemo;

	}

	@Override
	public Void apply(Object context, Object d, int index) {
		Platform.runLater(() -> {
			scatterPlotMatrixDemo.brushEnd();
		});
		return null;
	}
}
