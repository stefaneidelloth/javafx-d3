package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;


public class YCaptureDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> yList;
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YCaptureDataFunction(JsEngine engine,  List<Double> yList){
		this.engine=engine;
		this.yList = yList;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
		
		Coords coords = ConversionUtil.convertObjectTo(datum, Coords.class, engine);	
		Double y = coords.y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
