package org.treez.javafxd3.d3.democases.functionplotter;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.functionplot.FunctionPlot;
import org.treez.javafxd3.functionplot.Options;

import javafx.scene.layout.VBox;

public class FunctionPlotDemo extends AbstractDemoCase {

	//#region CONSTRUCTORS

	public FunctionPlotDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new FunctionPlotDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		getSvg() //
				.attr("width", 700) //
				.attr("height", 400);

		Options options = new Options(engine) //
				.setTarget("#svg") //
				.setDataExpression("[{fn: 'exp(-x^2)'}]");
		
		FunctionPlot functionPlot = new FunctionPlot(engine);
		functionPlot.apply(options);
		
	}

	@Override
	public void stop() {

	}

	//#end region

}
