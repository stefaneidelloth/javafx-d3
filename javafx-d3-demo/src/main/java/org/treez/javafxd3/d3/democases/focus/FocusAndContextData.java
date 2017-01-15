package org.treez.javafxd3.d3.democases.focus;

import org.treez.javafxd3.d3.time.JsDate;

public class FocusAndContextData {

	//#region ATTRIOBUTES

	private final JsDate date;

	private final double price;

	//#end region

	//#region CONSTRUCTORS

	public FocusAndContextData(final JsDate date, final double price) {
		super();
		this.date = date;
		this.price = price;
	}

	//#end region

	//#region ACCESSORS

	public JsDate getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}

	//#end region
}
