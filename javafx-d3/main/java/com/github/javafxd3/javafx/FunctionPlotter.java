package com.github.javafxd3.javafx;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.javafxd3.functionplot.FunctionPlot;
import com.github.javafxd3.functionplot.Options;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;

public class FunctionPlotter extends Region {

	//#region ATTRIBUTES

	private JavaFxD3Browser browser;

	private FunctionPlot functionPlot;

	private Options options;

	private Queue<PlotInstruction> queue;

	private boolean isInitialized = false;

	//#end region

	//#region CONSTRUCTORS

	public FunctionPlotter() {
		queue = new LinkedBlockingQueue<PlotInstruction>();
		createJavaFxD3Browser();
	}

	//#end region

	//#region METHODS

	//#region INITIALIZATION

	private void createJavaFxD3Browser() {

		Runnable loadingFinishedRunnable = () -> {
			initializeFunctionPlot();
			processQueue();
			isInitialized = true;
		};

		browser = new JavaFxD3Browser(loadingFinishedRunnable, false);
		FunctionPlotter.this.getChildren().add(browser);

	}

	private void processQueue() {
		while (!queue.isEmpty()) {
			PlotInstruction plotInstruction = queue.poll();
			plotInstruction.execute(this);
		}
	}

	private void initializeFunctionPlot() {
		functionPlot = browser.getFunctionPlot();
		WebEngine webEngine = browser.getWebEngine();
		options = new Options(webEngine);
		options.setTarget("#svg");

	}

	//#end region

	//#region PLOT

	public void plot(String dataExpression) {
		if (isInitialized) {
			plotExecute(dataExpression);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.plotExecute(dataExpression);
			queue.add(plotInstruction);
		}
	}

	private void plotExecute(String dataExpression) {
		options.setDataExpression(dataExpression);
		functionPlot.apply(options);
	}

	//#end region

	//#region X DOMAIN

	public void setXDomain(double xMin, double xMax) {
		if (isInitialized) {
			setXDomainExecute(xMin, xMax);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.setXDomainExecute(xMin, xMax);
			queue.add(plotInstruction);
		}
	}

	private void setXDomainExecute(double xMin, double xMax) {
		options.setXDomain(xMin, xMax);
		functionPlot.apply(options);
	}

	//#end region

	//#region Y DOMAIN

	public void setYDomain(double yMin, double yMax) {
		if (isInitialized) {
			setYDomainExecute(yMin, yMax);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.setYDomainExecute(yMin, yMax);
			queue.add(plotInstruction);
		}
	}

	private void setYDomainExecute(double yMin, double yMax) {
		options.setYDomain(yMin, yMax);
		functionPlot.apply(options);
	}

	//#end region

	//#end region

	//#end region

}
