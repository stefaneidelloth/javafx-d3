package com.github.javafxd3.demo.client;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

/**
 * Abstract parent class for all demo cases
 * 
 */
public abstract class AbstractDemoCase implements DemoCase {

	// #region ATTRIBUTES

	/**
	 * The d3 wrapper
	 */
	protected D3 d3;

	/**
	 * Controls the browser
	 */
	protected WebEngine webEngine;

	/**
	 * Box for demo case preferences and buttons
	 */
	protected VBox demoPreferenceBox;

	// #end region

	// #region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public AbstractDemoCase(D3 d3, VBox demoPreferenceBox) {
		this.d3 = d3;
		this.webEngine = d3.getWebEngine();
		this.demoPreferenceBox = demoPreferenceBox;
	}
	
	// #end region
	
	//#region METHODS
	
	/**
	 * Creates a new button on the demo preferences box
	 * @param label
	 * @param clickHandler
	 */
	protected void createButton(String label, EventHandler<ActionEvent> clickHandler) {
		Button button = new Button();		
		button.setText(label);
		button.onActionProperty().set(clickHandler);
		demoPreferenceBox.getChildren().add(button);
	}
	
	/**
	 * Clears the content of the svg element and returns
	 * the svg as Selection
	 * @return 
	 */
	public Selection clearSvg(){			
		Selection root = getSvg();
		root.selectAll("*").remove();
		return root;
	}

	/**
	 * @return
	 */
	public Selection getSvg() {
		Selection svg = d3.select("#svg");
		return svg;
	}
	
	
	//#end region

}
