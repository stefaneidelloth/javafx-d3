package com.github.javafxd3.demo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.scales.LinearScale;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * A JavaFx Node that shows d3 content on a JavaFx WebView.
 *
 */
public class JavaFxD3Browser extends Region {

	// #region ATTRIBUTES

	/**
	 * A JavaFx WebView (= "browser") that shows the html content that is
	 * created using the d3 wrapper
	 */
	private WebView browser;

	/**
	 * Controls the browser and provides access to JavaScript functionality
	 */
	private WebEngine webEngine;

	/**
	 * The d3 wrapper
	 */
	private D3 d3;

	/**
	 * This runnable is executed after the initial loading of the browser has
	 * been finished. This is required to ensure that the JavaScript d3 object
	 * already exists in the browser before retrieving it.
	 */
	private Runnable postLoadingFinishedHook;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param postLoadingFinishedHook
	 *            runnable that is executed after the initial loading has been
	 *            finished
	 */
	public JavaFxD3Browser(Runnable postLoadingFinishedHook) {
		this.postLoadingFinishedHook = postLoadingFinishedHook;
		initialize();
	}

	// #end region

	// #region METHODS

	private void initialize() {

		// create browser
		browser = new WebView();

		// add the browser to the JavaFx node
		getChildren().add(browser);

		// get web engine
		webEngine = browser.getEngine();

		// enable java script
		webEngine.setJavaScriptEnabled(true);

		// create handler for JavaScript alert event
		webEngine.setOnAlert((eventArgs) -> {
			String message = eventArgs.getData();
			showAlert(message);
		});

		// add loading finished listener
		ReadOnlyObjectProperty<State> state = webEngine.getLoadWorker().stateProperty();
		state.addListener((obs, oldState, newState) -> {

			boolean isSucceeded = (newState == Worker.State.SUCCEEDED);
			if (isSucceeded) {
				executeInitalJavaScriptToSetUpD3Wrapper();
				postLoadingFinishedHook.run();				
			}

			boolean isFailed = (newState == Worker.State.FAILED);
			if (isFailed) {
				System.out.println("Loading initial html page failed");
			}

		});

		// load the initial browser content
		String initialBrowserContent = createInitialBrowserContent();
		webEngine.loadContent(initialBrowserContent);

		// URL url = JavaFxD3Browser.class.getResource("d3.html");
		// String urlPath = url.toExternalForm();
		// webEngine.load(urlPath);

		// note: see method

	}

	/**
	 * Shows an alert message for the user
	 * 
	 * @param message
	 */
	public void showAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	/**
	 * Creates tha html content that will be initially loaded in the browser
	 * before executing any JavaScript
	 * 
	 * @return
	 */
	private String createInitialBrowserContent() {
		String htmlContent = "<!DOCTYPE html>\n" + 
							 "<meta charset=\"utf-8\">\n" + 							
							 "<svg id=\"svg\" class=\"svg\"></svg>\n";
		return htmlContent;
	}

	private void executeInitalJavaScriptToSetUpD3Wrapper() {

		// inject d3 into web engine
		String d3Content = getD3ContentFromFile("d3.js");
		webEngine.executeScript(d3Content);
		
		// inject firebug, see https://stackoverflow.com/questions/9398879/html-javascript-debugging-in-javafx-webview
		String fireBugCommand = "if (!document.getElementById('FirebugLite')){"
				+ "E = document['createElement' + 'NS'] && "
				+ "document.documentElement.namespaceURI;E = E ? "
				+ "document['createElement' + 'NS'](E, 'script') : "
				+ "document['createElement']('script');"
				+ "E['setAttribute']('id', 'FirebugLite');"
				+ "E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');"
				+ "E['setAttribute']('FirebugLite', '4');"
				+ "(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);"
				+ "E = new Image;"
				+ "E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');"
				+ "}";
		
		webEngine.executeScript(fireBugCommand);

		// create d3 wrapper
		d3 = new D3(webEngine);

		// do some example d3 stuff
		Double[] data = { 4.0, 8.0, 15.0, 16.0, 23.0, 42.0 };

		Double width = 420.0;
		Double barHeight = 20.0;
		Double maxX = Collections.max(Arrays.asList(data));

		LinearScale x = d3.scale().linear().domain(0.0, maxX).range(0.0, width);

		d3.createJsVariable("x", x);

		Selection svg = d3.select(".svg").attr("width", width).attr("height", barHeight * data.length);
			
		String expression = "function(d, i) { return \"translate(0,\" + i *" + barHeight + " + \")\"; }";
		Selection bar = svg.selectAll("g").data(data).enter().append("g").attrExpression("transform", expression);

		Selection rect = bar.append("rect").attr("width", x).attr("height", barHeight - 1);
		String rectStyle = "fill: steelblue;";
		rect.attr("style", rectStyle);
		
		Selection text = bar.append("text").attrExpression("x", "function(d) { return x(d) - 3; }").attr("y", barHeight / 2)
				.attr("dy", ".35em").textExpression("function(d) { return d; }");
		
		String textStyle="fill: white; font: 10px sans-serif; text-anchor: end;";
		text.attr("style", textStyle);
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height) {
		return 750;
	}

	@Override
	protected double computePrefHeight(double width) {
		return 500;
	}

	private String getD3ContentFromFile(String fileName) {

		StringBuilder d3Contents = new StringBuilder();		
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF8"));
			String line = reader.readLine();
			while (line != null) {
				d3Contents.append(line);
				line = reader.readLine();
			}
		} catch (IOException exception) {
			return null;
		}
		return d3Contents.toString();

	}

	// #end region

	// #region ACCESSORS

	/**
	 * 
	 * @return
	 */
	public D3 getD3() {
		if (d3 == null) {
			String message = "The d3 reference is null. Do not call this method directly but use "
					+ "the post loading hook to wait " + "until the initial loading of the browser has been finished.";
			throw new IllegalStateException(message);
		}
		return d3;
	}

	// #end region

}