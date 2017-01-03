package org.treez.javafxd3.plotly.data.contour.colorbar;



import java.util.ArrayList;
import java.util.List;

public enum TickMode {

	//#region VALUES

	AUTO("auto"), //
	LINEAR("linar"), //
	ARRAY("array");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	TickMode(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static TickMode fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (TickMode enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
