package com.github.javafxd3.demo.client.democases.svg.line;

import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;

public class DefinedDatumFunction implements DatumFunction<Boolean> {
	@Override
	public Boolean apply(Object context, Object d, int index) {

		Value datum = (Value) d;

		return datum.<CustomCoords> as().defined;
	}
	
	public Boolean apply(String context, String d, int index){
		return null;
	}
}