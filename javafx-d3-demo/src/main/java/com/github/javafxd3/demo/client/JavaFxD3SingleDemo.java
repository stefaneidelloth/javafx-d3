package com.github.javafxd3.demo.client;

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
		
		//create browser
		Runnable postLoadingHook = ()->{System.out.println("Initial loading is finished");};
		JavaFxD3Browser browser = new JavaFxD3Browser(postLoadingHook);
		
		//create the scene
		scene = new Scene(browser, 750, 500, Color.web("#666970"));
		stage.setScene(scene);
		stage.show();
	}
	
	
	//#end region

}


