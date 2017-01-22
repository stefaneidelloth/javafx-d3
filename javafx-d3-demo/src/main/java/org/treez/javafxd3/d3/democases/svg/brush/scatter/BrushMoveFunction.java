package org.treez.javafxd3.d3.democases.svg.brush.scatter;

import org.treez.javafxd3.d3.functions.DataFunction;

import javafx.application.Platform;

public class BrushMoveFunction implements DataFunction<Void> {

	private ScatterPlotMatrixDemo scatterPlotMatrixDemo;

	public BrushMoveFunction(ScatterPlotMatrixDemo scatterPlotMatrixDemo) {
		this.scatterPlotMatrixDemo = scatterPlotMatrixDemo;
	}

	@Override
	public Void apply(Object context, Object d, int index) {
		Platform.runLater(() -> {
			Point point = (Point) d;
			scatterPlotMatrixDemo.brushMove(point);
		});
		return null;
	}
}
