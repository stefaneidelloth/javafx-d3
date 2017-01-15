package org.treez.javafxd3.d3.democases.axis;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeFormat;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class DataDsvObjectAccessor implements DsvObjectAccessor<DsvData> {
	
	//#region ATTRIBUTES
	
	private WebEngine webEngine;
	private TimeFormat format;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DataDsvObjectAccessor(WebEngine webEngine, TimeFormat format){
		this.webEngine = webEngine;
		this.format = format;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public DsvData apply(Object row, int index) {
			
		JSObject jsRow = (JSObject) row;
		DsvRow dsvRow = new DsvRow(webEngine, jsRow);
	
		Value value = dsvRow.get("symbol");
		
		if ("S&P 500".equals(value.asString())) {
			String symbol = dsvRow.get("symbol").asString();
			JsDate date = format.parse(dsvRow.get("date").asString());
			double price = dsvRow.get("price").asDouble();
			return new DsvData(webEngine, symbol, date, price);
		} else {
			return null;
		}
	}
		
	//#end region

}
