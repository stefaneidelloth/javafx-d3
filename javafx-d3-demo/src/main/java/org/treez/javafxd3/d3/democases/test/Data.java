package org.treez.javafxd3.d3.democases.test;

import java.util.Date;

/**
 * An exemple data parsed from the file test-data/readme.csv
 * 
 */
public class Data {

	//#region ATTRIBUTES
	private final String symbol;

	private final Date date;

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
	public Data(final String symbol, final Date date, final double price) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.price = price;
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
	public Date getDate() {
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