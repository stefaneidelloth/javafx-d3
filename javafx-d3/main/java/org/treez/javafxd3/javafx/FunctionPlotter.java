package org.treez.javafxd3.javafx;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.treez.javafxd3.functionplot.FunctionPlot;
import org.treez.javafxd3.functionplot.Options;

import javafx.scene.layout.Region;

/**
 * Plots simple functional expressions, e.g. x^2
 */
public class FunctionPlotter extends Region {

	//#region ATTRIBUTES

	private JavaFxD3Browser browser;

	private FunctionPlot functionPlot;
	
	private Queue<PlotInstruction> queue;

	private boolean isInitialized = false;
	
	private double width;
	private double height;

	//#end region

	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 */
	public FunctionPlotter() {
		this(500,500);
	}

	/**
	 * Constructor
	 * @param width 
	 * @param height 
	 */
	public FunctionPlotter(double width, double height) {
		this.width = width;
		this.height = height;
		queue = new LinkedBlockingQueue<PlotInstruction>();
		createJavaFxD3Browser();
	}

	//#end region

	//#region METHODS

	//#region INITIALIZATION

	private void createJavaFxD3Browser() {

		Runnable loadingFinishedRunnable = () -> {
			functionPlot = browser.getFunctionPlot();
			processQueue();
			isInitialized = true;
		};

		browser = new JavaFxD3Browser(loadingFinishedRunnable, false);
		browser.setBrowserWidth(width);
		browser.setBrowserHeight(height);
		FunctionPlotter.this.getChildren().add(browser);

	}

	private void processQueue() {
		while (!queue.isEmpty()) {
			PlotInstruction plotInstruction = queue.poll();
			plotInstruction.execute(this);
		}
	}


	//#end region

	//#region PLOT

	/**
	 * Plots the given expression, e.g. "x^2"
	 * 
	 * @param dataExpression
	 */
	public void plot(String dataExpression) {
		if (isInitialized) {
			plotExecute(dataExpression);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.plotExecute(dataExpression);
			queue.add(plotInstruction);
		}
	}

	private void plotExecute(String expression) {
		Options options = functionPlot.getOptions();	
		options.setWidth(width);
		options.setHeight(height);
		String dataExpression = "[{fn: '" + expression + "'}]";				
		options.setDataExpression(dataExpression);
		functionPlot.apply(options);
	}
	
	/**
	 * Plots the given custom expression, e.g. "[{fn: '3 + sin(x)', range: [2, 8], closed: true }]" 
	 * @param customExpression
	 */
	public void plotCustomExpression(String customExpression) {
		if (isInitialized) {
			plotCustomExpressionExecute(customExpression);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.plotCustomExpressionExecute(customExpression);
			queue.add(plotInstruction);
		}
		
	}
	
	private void plotCustomExpressionExecute(String customExpression) {
		Options options = functionPlot.getOptions();	
		options.setWidth(width);
		options.setHeight(height);					
		options.setDataExpression(customExpression);
		functionPlot.apply(options);
		
	}
	

	//#end region

	//#region X DOMAIN

	/**
	 * Sets the limits for the x axis
	 * @param xMin
	 * @param xMax
	 */
	public void setXDomain(double xMin, double xMax) {
		if (isInitialized) {
			setXDomainExecute(xMin, xMax);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.setXDomainExecute(xMin, xMax);
			queue.add(plotInstruction);
		}
	}

	private void setXDomainExecute(double xMin, double xMax) {
		Options options = functionPlot.getOptions();
		options.setXDomain(xMin, xMax);
		functionPlot.apply(options);
	}

	//#end region

	//#region Y DOMAIN

	/**
	 * Sets the limits for the y axis
	 * @param yMin
	 * @param yMax
	 */
	public void setYDomain(double yMin, double yMax) {
		if (isInitialized) {
			setYDomainExecute(yMin, yMax);
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.setYDomainExecute(yMin, yMax);
			queue.add(plotInstruction);
		}
	}

	private void setYDomainExecute(double yMin, double yMax) {
		Options options = functionPlot.getOptions();
		options.setYDomain(yMin, yMax);
		functionPlot.apply(options);
	}	
	
	//#end region
	
	//#region SHOW ERROR
	
	public void showError() {
		if (isInitialized) {
			showErrorExecute();
		} else {
			PlotInstruction plotInstruction = (plotter) -> plotter.showErrorExecute();
			queue.add(plotInstruction);
		}		
	}
	
	private void showErrorExecute(){
		functionPlot.showError();
	}

	
	
	//#end region
	


	//#end region

	//#end region

}
