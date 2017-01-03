package org.treez.javafxd3.plotly.data.contour.colorbar;



import java.util.ArrayList;
import java.util.List;

public enum LenMode {

	//#region VALUES

	FRACTION("fraction"), //
	PIXELS("pixels");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	LenMode(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static LenMode fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (LenMode enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
