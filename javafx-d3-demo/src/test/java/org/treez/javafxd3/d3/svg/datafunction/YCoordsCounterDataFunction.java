package org.treez.javafxd3.d3.svg.datafunction;

import java.util.List;

import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.functions.DataFunction;

public class YCoordsCounterDataFunction implements DataFunction<Double> {
	
	//#region ATTRIBUTES
	
	private JsEngine engine;
	
	private List<Double> yList;
	
	private Coords counter;
	
	
	
	//#end region
	
	//#region CONSTRUCTORS
	
	public YCoordsCounterDataFunction(JsEngine engine,  List<Double> yList, Coords counter){
		this.engine=engine;
		this.yList = yList;
		this.counter = counter;
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public Double apply(Object context, Object datum, int index) {
			
		counter.y(counter.y() + 1);
		
		Coords coords = ConversionUtil.convertObjectTo(datum, Coords.class, engine);
		Double y = coords.y();
		yList.add(y);
		return y;
	}
	
	//#end region

}
