package org.treez.javafxd3.plotly.data.line;

import java.util.ArrayList;
import java.util.List;

/**
 * Determines the line shape. With "spline" the lines are drawn using spline interpolation.
 * The other available values correspond to step-wise line shapes. 
 */
public enum LineShape {

	//#region VALUES

	LINEAR("linear"), //
	SPLINE("spline"), //
	HV("hv"), //
	VH("vh"), //
	HVH("hvh"), //
	VHV("vhv"); //
	

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	LineShape(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static LineShape fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (LineShape enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
