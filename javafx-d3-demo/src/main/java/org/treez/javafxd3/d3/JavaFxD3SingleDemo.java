package org.treez.javafxd3.d3;

import java.util.Arrays;
import java.util.Collections;

import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.scales.LinearScale;

import org.treez.javafxd3.javafx.JavaFxD3Browser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Demonstrates how d3.js can be used with a JavaFx WebView
 *
 */
public class JavaFxD3SingleDemo extends Application {

	//#region ATTRIBUTES

	/**
	 * The JavaFx scene
	 */
	private Scene scene;

	private JavaFxD3Browser browser;

	//#end region

	//#region METHODS

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		//set state title
		stage.setTitle("Single javafx-d3 demo");

		//define d3 content as post loading hook
		Runnable postLoadingHook = () -> {
			System.out.println("Initial loading of browser is finished");

			//do some d3 stuff
			createD3Example();

		};

		//create browser
		browser = new JavaFxD3Browser(postLoadingHook, true);

		//create the scene
		scene = new Scene(browser, 750, 500, Color.web("#666970"));
		stage.setScene(scene);
		stage.show();

	}

	private void createD3Example() {

		D3 d3 = browser.getD3();		

		Double[] data = { 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 };

		Double width = 420.0;
		Double barHeight = 20.0;
		Double maxX = Collections.max(Arrays.asList(data));

		LinearScale x = d3.scale() //
				.linear() //
				.domain(0.0, maxX) //
				.range(0.0, width);

		d3.createJsVariable("x", x);

		Selection svg = d3.select(".svg") //
				.attr("width", width) //
				.attr("height", barHeight * data.length);

		String transformExpression = "function(d, i) { return \"translate(0,\" + i *" + barHeight + " + \")\"; }";

		Selection bar = svg.selectAll("g") //
				.data(data) //
				.enter() //
				.append("g") //
				.attrExpression("transform", transformExpression);

		String rectStyle = "fill: steelblue;";

		bar.append("rect") //
				.attr("width", x) //
				.attr("height", barHeight - 1) //
				.attr("style", rectStyle);

		String textStyle = "fill: white; font: 10px sans-serif; text-anchor: end;";

		bar.append("text") //
				.attrExpression("x", "function(d) { return x(d) - 3; }") //
				.attr("y", barHeight / 2) //
				.attr("dy", ".35em") //
				.textExpression("function(d) { return d; }") //
				.attr("style", textStyle);

	}

	//#end region

}
