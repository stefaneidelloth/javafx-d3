package org.treez.javafxd3.d3.democases.axis;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.time.JsDate;
import org.treez.javafxd3.d3.time.TimeFormat;

public class DataDsvObjectAccessor implements DsvObjectAccessor<DsvData> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	private TimeFormat format;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public DataDsvObjectAccessor(JsEngine engine, TimeFormat format){
		this.engine = engine;
		this.format = format;
	}
	
	//#end region
	
	//#region METHODS
	
	@Override
	public DsvData apply(Object row, int index) {			
		
		DsvRow dsvRow = ConversionUtil.convertObjectTo(row,  DsvRow.class, engine);
	
		Value value = dsvRow.get("symbol");
		
		if ("S&P 500".equals(value.asString())) {
			String symbol = dsvRow.get("symbol").asString();
			JsDate date = format.parse(dsvRow.get("date").asString());
			double price = dsvRow.get("price").asDouble();
			return new DsvData(engine, symbol, date, price);
		} else {
			return null;
		}
	}
		
	//#end region

}
