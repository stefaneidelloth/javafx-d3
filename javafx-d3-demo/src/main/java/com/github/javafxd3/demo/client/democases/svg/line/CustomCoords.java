package com.github.javafxd3.demo.client.democases.svg.line;

import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.functions.DatumFunction;

import javafx.scene.web.WebEngine;

public  class CustomCoords extends Coords {

	//#region ATTRIBUTES

	boolean defined;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * 
	 * @param x
	 * @param y
	 * @param defined
	 */
	public CustomCoords(WebEngine webEngine, double x, double y, boolean defined) {
		super(webEngine, x, y);
		this.defined = defined;
	}

	//#end region

	//#region METHODS

	public static DatumFunction<Double> xAccessor() {
		return new XAccessorDatumFunction();
	}

	public static DatumFunction<Double> yAccessor() {
		return new YAccessorDatumFunction();
	}

	public static DatumFunction<Boolean> definedAccessor() {
		return new DefinedDatumFunction();
	}

	//#end region

	//#region CLASSES
	
	

	

	//#end region

}
