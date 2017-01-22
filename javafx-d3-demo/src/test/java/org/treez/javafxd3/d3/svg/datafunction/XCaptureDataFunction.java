package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;


public class XCaptureDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> xList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public XCaptureDataFunction(JsEngine engine,  List<Double> xList){
		this.engine=engine;
		this.xList = xList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		Coords coords = ConversionUtil.convertObjectTo(datum, Coords.class, engine);
		Double x = coords.x();
		xList.add(x);
		return x;
	}
	
	//#end region

}
