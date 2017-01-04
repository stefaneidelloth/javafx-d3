package org.treez.javafxd3.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.functionplot.FunctionPlot;
import org.treez.javafxd3.plotly.Plotly;

import javafx.application.Platform;
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

	//#region ATTRIBUTES

	/**
	 * A JavaFx WebView (= "browser") that shows the html content that is
	 * created using the d3 wrapper
	 */
	private WebView webView;

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
	 * been finished. Put your custom code into this runnable. This work flow
	 * ensures that the JavaScript d3 object already exists in the browser
	 * before your custom code makes use of it.
	 */
	private Runnable loadingFinishedHook;

	private Boolean enableDebugMode = false;
	
	private double browserWidth = 900;
	private double browserHeight = 1000;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param loadingFinishedHook
	 *            This runnable is executed after the initial loading of the
	 *            browser has been finished. Put your custom code into this
	 *            runnable. This work flow ensures that the JavaScript d3 object
	 *            already exists in the browser before your custom code makes
	 *            use of it.
	 */
	public JavaFxD3Browser(Runnable loadingFinishedHook) {
		this.loadingFinishedHook = loadingFinishedHook;
		initialize();
	}

	/**
	 * Constructor with possibility to enable debug mode (= show fire bug)
	 * 
	 * @param loadingFinishedHook
	 * @param enableDebugMode 
	 */
	public JavaFxD3Browser(Runnable loadingFinishedHook, Boolean enableDebugMode) {
		this.loadingFinishedHook = loadingFinishedHook;
		this.enableDebugMode = enableDebugMode;
		initialize();
	}

	//#end region

	//#region METHODS

	private void initialize() {

		this.setPrefSize(browserWidth, browserHeight);
		
		//create web view
		webView = new WebView();		
		webView.setPrefSize(browserWidth, browserHeight);
		
		//add the web view as child to this JavaFx region
		getChildren().add(webView);

		//get web engine
		webEngine = webView.getEngine();
		Objects.requireNonNull(webEngine);

		//enable java script
		webEngine.setJavaScriptEnabled(true);

		//create handler for JavaScript alert event
		webEngine.setOnAlert((eventArgs) -> {
			String message = eventArgs.getData();
			System.out.println("JavaFxD3Browser-Alert: " + message);
			showAlert(message);
		});

		//add loading finished hook 
		registerLoadingFinishedHook();

		//load the initial browser content
		String initialBrowserContent = createInitialBrowserContent();

		//loadContent(initialBrowserContent);
		webEngine.loadContent(initialBrowserContent);

		//note: after asynchronous loading has been finished, the
		//loading finished hook will be executed.

	}

	private void registerLoadingFinishedHook() {
		Worker<Void> loadWorker = webEngine.getLoadWorker();
		ReadOnlyObjectProperty<State> state = loadWorker.stateProperty();
		state.addListener((obs, oldState, newState) -> {

			boolean isSucceeded = (newState == Worker.State.SUCCEEDED);
			if (isSucceeded) {
				injectJavaScriptLibraries();
				createD3Wrapper();
				injectSaveHelper();
				if (loadingFinishedHook != null) {
					loadingFinishedHook.run();
				}
			}

			boolean isFailed = (newState == Worker.State.FAILED);
			if (isFailed) {
				System.out.println("Loading initial html page failed");
			}

		});
	}

	private void injectJavaScriptLibraries() {

		injectD3();
		injectFunctionPlotter();
		injectNvd3();	
		injectPlotly();
		injectJQuery();

		if (enableDebugMode) {
			injectFireBug();
		}

	}

	private void injectD3() {
		// https://github.com/mbostock/d3/blob/master/d3.min.js
		String d3Content = getJavaScriptLibraryFromFile("d3.min.js");
		webEngine.executeScript(d3Content);
	}

	private void injectFunctionPlotter() {
		// https://github.com/maurizzzio/function-plot/blob/master/dist/function-plot.js
		String functionPlotterContent = getJavaScriptLibraryFromFile("function-plot.js");
		webEngine.executeScript(functionPlotterContent);
	}

	private void injectNvd3() {
		// https://github.com/novus/nvd3/blob/master/build/nv.d3.min.js
		String nvd3Content = getJavaScriptLibraryFromFile("nv.d3.min.js");
		webEngine.executeScript(nvd3Content);
	}	
	
	private void injectPlotly() {
		// https://code.jquery.com/jquery-2.2.4.js
		String jQueryContent = getJavaScriptLibraryFromFile("jquery-2.2.4.min.js");
		webEngine.executeScript(jQueryContent);
	}	
	
	private void injectJQuery() {
		// https://github.com/plotly/plotly.js/
		String plotlyContent = getJavaScriptLibraryFromFile("plotly.min.js");
		webEngine.executeScript(plotlyContent);
	}

	private void injectFireBug() {
		// inject firebug into web engine, also see 
		// https://stackoverflow.com/questions/9398879/html-javascript-debugging-in-javafx-webview
		// and
		// https://getfirebug.com/firebug-lite.js#startOpened
		//
		String fireBugCommand = "if (!document.getElementById('FirebugLite')){"
				+ "E = document['createElement' + 'NS'] && " + "document.documentElement.namespaceURI;E = E ? "
				+ "document['createElement' + 'NS'](E, 'script') : " + "document['createElement']('script');"
				+ "E['setAttribute']('id', 'FirebugLite');"
				+ "E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');"
				+ "E['setAttribute']('FirebugLite', '4');"
				+ "(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);"
				+ "E = new Image;" + "E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');" + "}";

		webEngine.executeScript(fireBugCommand);
	}

	private void createD3Wrapper() {
		d3 = new D3(webEngine);
	}

	private void injectSaveHelper() {
		SaveHelper saveHelper = new SaveHelper();
		d3.setMember("saveHelper", saveHelper);

	}

	/**
	 * Shows an alert message for the user
	 * 
	 * @param message
	 */
	public void showAlert(String message) {
		Runnable alertRunnable = () -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText(message);
			alert.showAndWait();
		};
		Platform.runLater(alertRunnable);
	}

	/**
	 * Creates the html content that will be initially loaded in the browser
	 * before executing any JavaScript
	 * 
	 * @return
	 */
	private String createInitialBrowserContent() {
		String htmlContent = "<!DOCTYPE html>\n" //
				+ "<meta charset=\"utf-8\">\n" //	
				+ "<body style = \"margin:0;padding:0;\">"	
				+ "<div id = \"dummyDiv\"></div>\n"
				+ "<div id=\"invisibleDummyDiv\" style=\"display: none;\"></div>\n"
				+ "<div id = \"root\" ondblclick=\"saveSvg()\" style = \"margin:0;padding:0;font-family:Consolas;font-size:small;\">\n" //
				+ "<svg id=\"svg\" class=\"svg\"></svg>\n"//
				+ "</div>\n"				
				+ "</body>"
				+ "<script>\n" //
				+ "function saveSvg(e){\n" //				
				+ "	  var svg = document.getElementById('svg');\n" //		
				+ "	  var svgXml = (new XMLSerializer).serializeToString(svg);\n" //	
				+ "   d3.saveHelper.saveSvg(svgXml);\n"				
				+ "}\n" //
				+ "</script>\n";				
				
		return htmlContent;
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(webView, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height) {
		return browserWidth;
	}

	@Override
	protected double computePrefHeight(double width) {
		return browserHeight;
	}

	private String getJavaScriptLibraryFromFile(String fileName) {

		StringBuilder libraryContents = new StringBuilder();

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF8"));
			String line = reader.readLine();
			while (line != null) {
				libraryContents.append(line);
				line = reader.readLine();
			}
		} catch (IOException exception) {
			return null;
		}
		return libraryContents.toString();

	}

	//#end region

	//#region ACCESSORS

	
	public D3 getD3() {
		if (d3 == null) {
			String message = "The d3 reference is null. Do not call this method directly but use "
					+ "the post loading hook to wait " + "until the initial loading of the browser has been finished.";
			throw new IllegalStateException(message);
		}
		return d3;
	}

	
	public FunctionPlot getFunctionPlot() {
		if (d3 == null) {
			String message = "The d3 reference is null. Do not call this method directly but use "
					+ "the post loading hook to wait until the initial loading of the browser has been finished.";
			throw new IllegalStateException(message);
		}

		FunctionPlot functionPlot = new FunctionPlot(webEngine);
		return functionPlot;
	}
	
	public Plotly getPlotly() {
		if (d3 == null) {
			String message = "The d3 reference is null. Do not call this method directly but use "
					+ "the post loading hook to wait until the initial loading of the browser has been finished.";
			throw new IllegalStateException(message);
		}

		Plotly plotly = new Plotly(webEngine);
		return plotly;
	}
	
	

	
	public WebEngine getWebEngine() {
		return webEngine;
	}

	
	public void setBrowserWidth(double width) {
		browserWidth = width+4;		
		
		this.setPrefSize(browserWidth, browserHeight);		
		webView.setPrefSize(browserWidth, browserHeight);
		//this.resize(browserWidth, browserHeight);
		//this.layout();
		//this.getParent().layout();
		
		
		
	}
	
	/**
	 * @param height
	 */
	public void setBrowserHeight(double height) {
		browserHeight = height+4;
		this.setHeight(height);
		this.setPrefSize(browserWidth, browserHeight);
		webView.setPrefSize(browserWidth, browserHeight);
		//this.resize(browserWidth, browserHeight);
		//this.layout();
		//this.getParent().layout();
		//this.layoutChildren();
		
	}

	//#end region

}