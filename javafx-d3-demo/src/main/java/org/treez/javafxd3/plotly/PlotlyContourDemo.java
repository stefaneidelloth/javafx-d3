package org.treez.javafxd3.plotly;

import org.treez.javafxd3.javafx.JavaFxD3Browser;
import org.treez.javafxd3.plotly.configuration.Configuration;
import org.treez.javafxd3.plotly.data.PlotlyType;
import org.treez.javafxd3.plotly.data.SingleData;
import org.treez.javafxd3.plotly.layout.Layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlotlyContourDemo extends Application {

	//#region METHODS

	private Scene scene;

	private JavaFxD3Browser browser;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		stage.setTitle("Plotly demo");

		//define d3 content as post loading hook
		Runnable postLoadingHook = () -> {
			System.out.println("Initial loading of browser is finished");

			//do some d3 stuff
			createPlotlyExample();

		};

		//create browser
		browser = new JavaFxD3Browser(postLoadingHook, true);

		//create the scene
		scene = new Scene(browser, 750, 1000, Color.web("#666970"));
		stage.setScene(scene);
		stage.show();
	}

	private void createPlotlyExample() {
		Plotly plotly = browser.getPlotly();

		Double[] xData = { 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 };
		Double[] yData = { 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 };
		Double[][] zData = {
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				{ 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 },
				}; 

		SingleData singleData = plotly.createSingleData();
		singleData.setX(xData);
		singleData.setY(yData);
		singleData.setZ(zData);
		singleData.setType(PlotlyType.CONTOUR);

		Layout layout = plotly.createLayout();

		Configuration configuration = plotly.createConfiguration();

		plotly.newPlot("dummyDiv", singleData, layout, configuration);		
		
		String copyCommand = "$('.main-svg').find('.contour').appendTo('.svg');";
		plotly.eval(copyCommand);
		
		//String clearCommand = "$('#dummyDiv').empty()";
		//plotly.eval(clearCommand);
		
				
		

	}

	//#end region

}
