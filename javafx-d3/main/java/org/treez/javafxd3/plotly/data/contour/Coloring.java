package org.treez.javafxd3.plotly.data.contour;

import java.util.ArrayList;
import java.util.List;



public enum Coloring implements org.treez.javafxd3.javafx.EnumValueProvider<Coloring>{

	//#region VALUES

	FILL("fill"), //
	HEATMAP("heatmap"), //
	LINES("lines");	// ("none" is not used here) 

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	Coloring(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	@Override
	public Coloring fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	@Override
	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (Coloring enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
