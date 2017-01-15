package org.treez.javafxd3.d3.functions.callback;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.dsv.DsvCallback;

import javafx.scene.web.WebEngine;

public class DsvCallbackWrapper<A> implements DsvCallback<A> {

	//#region ATTRIBUTES

	private WebEngine webEngine = null;

	private PlainCallback<Array<A>> plainCallback = null;

	//#end region

	//#region CONSTRUCTORS

	public DsvCallbackWrapper(WebEngine webEngine, PlainCallback<Array<A>> plainCallback) {
		this.webEngine = webEngine;
		this.plainCallback = plainCallback;		
	}

	//#end region

	//#region METHODS

	@Override
	public void get(Object error, Object dataArrayObj) {

		@SuppressWarnings("unchecked")
		Array<A> dataArray = (Array<A>) ConversionUtil.convertObjectTo(dataArrayObj, Array.class, webEngine);
		plainCallback.call(dataArray);
	}

	//#end region

}
