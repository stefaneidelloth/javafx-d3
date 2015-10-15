package com.github.javafxd3.demo.client.democases.svg.line;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

public class YAccessorDatumFunction implements DatumFunction<Double> {
	@Override
	public Double apply(Object context, Object d, int index) {

		Value datum = (Value) d;

		return datum.<CustomCoords> as().y();
	}
	
	public Double apply(String context, String d, int index){
		return null;
	}
}
