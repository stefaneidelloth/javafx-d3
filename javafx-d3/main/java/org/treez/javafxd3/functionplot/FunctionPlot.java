package org.treez.javafxd3.functionplot;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

@SuppressWarnings("javadoc")
public class FunctionPlot extends JavaScriptObject {

	//#region ATTRIBUTES

	private JsObject instance;

	//#end region

	//#region CONSTRUCTORS

	public FunctionPlot(JsEngine engine) {
		super(engine);
		JsObject functionPlot = (JsObject) engine.executeScript("functionPlot");
		setJsObject(functionPlot);
	}

	//#end region

	//#region METHODS

	public void apply(Options options) {
		hideError();

		//plot
		JsObject jsOptions = options.getJsObject();
		instance = callThisForJsObject(jsOptions);
		
		//format plot
		setAxisNumberFormats();
		setLeftMargin();	
		instance.eval("this.draw()"); //redraws the plot to apply new format

	}

	private void setLeftMargin() {
		String command = "this.meta.margin;";
		JsObject margin = (JsObject) instance.eval(command);
		margin.eval("this.left=45;");
	}

	private void setAxisNumberFormats() {
		String xFormatCommand = "var isExp = false;"
				+ "this.meta.xAxis.tickFormat(" //
				+ "  function(d){ " //
				+ "    var abs = Math.abs(d);"
				+ "    if(abs != 0 && abs <= 1e-2 || abs >= 1e4 ){ " //
				+ "      return d3.format('e')(d);" //
				+ "      isExp = true;"
				+ "    } else { " //
				+ "      return d3.format('n')(d);" //
				+ "    }" //
				+ "  }" //
				+ ");"
				+ "if (isExp){"
				+ "  this.meta.xAxis.ticks(5);"
				+ "} else {" //
				+ "  this.meta.xAxis.ticks(10);"
				+ "}";
		instance.eval(xFormatCommand);
		
		String yFormatCommand = "this.meta.yAxis.tickFormat(" //
				+ "  function(d){ " //
				+ "    var abs = Math.abs(d);"
				+ "    if(abs != 0 && abs <= 1e-2 || abs >= 1e4 ){ " //
				+ "      return d3.format('e')(d);" //
				+ "    } else { " //
				+ "      return d3.format('n')(d);" //
				+ "    }" //
				+ "  }" //
				+ ")";
		instance.eval(yFormatCommand);
	}

	/**
	 * Tries to retrieve the options of the current function plotter instance.
	 * If the options can not be retrieved, new default options are created and
	 * returned.
	 * 
	 * @return
	 */
	public Options getOptions() {
		if (instance != null) {
			JsObject JsOptions = (JsObject) instance.getMember("options");
			if (JsOptions == null) {
				return new Options(engine);
			}

			return new Options(engine, JsOptions);

		} else {
			return new Options(engine);
		}
	}

	/**
	 * Sets the size of the target of the plotter, e.g. the size of the svg
	 * element
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(double width, double height) {
		D3 d3 = new D3(engine);
		Options options = getOptions();

		//set size of root 	
		d3.select("#root") //
				.attr("width", width) //
				.attr("height", height);

		//d3.select("#root").attr("style","background-color:red;");

		//set size of plotter
		options.setWidth(width);
		options.setHeight(height);

		

		apply(options);

	}

	public void showError() {
		D3 d3 = new D3(engine);

		//set size of root 	
		d3.select("#root").attr("style", "background-color:red");

	}

	public void hideError() {
		D3 d3 = new D3(engine);

		//set size of root 	
		d3.select("#root").attr("style", "white");

	}

	//#end region

}
