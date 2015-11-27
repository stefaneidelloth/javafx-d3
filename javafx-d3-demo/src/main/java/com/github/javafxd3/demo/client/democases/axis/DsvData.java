package com.github.javafxd3.demo.client.democases.axis;

import com.github.javafxd3.api.time.JsDate;
import com.github.javafxd3.api.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DsvData extends JavaScriptObject {

	//#region ATTRIBUTES

	private final String symbol;

	private final JsDate date;

	private final double price;

	//#end region

	//#region CONSTRUCTORS
	
	/**
	 * Constructor
	 * 
	 * @param symbol
	 * @param date
	 * @param price
	 */
	public DsvData(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);		
		JSObject jsDate = (JSObject) wrappedJsObject.getMember("date");
		this.date = new JsDate(webEngine, jsDate);
		this.price =(Double) wrappedJsObject.getMember("price");
		this.symbol = (String) wrappedJsObject.getMember("symbol");				
	}

	/**
	 * Constructor
	 * 
	 * @param symbol
	 * @param date
	 * @param price
	 */
	public DsvData(WebEngine webEngine, final String symbol, final JsDate date, final double price) {
		super(webEngine);		
		this.date = date;
		this.price = price;
		this.symbol = symbol;
		String command = "{date: null, price: null, symbol: null}";
		JSObject jsObject = evalForJsObject(command);
		JSObject jsDate = date.getJsObject();
		jsObject.setMember("date", jsDate);
		jsObject.setMember("price", price);
		jsObject.setMember("symbol", symbol);
		setJsObject(jsObject);
				
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return "Data [date=" + date.getTime() + ", price=" + price + "]";
	}
	
	//#end region

	//#region ACCESSORS

	/**
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return
	 */
	public JsDate getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	//#end region
}