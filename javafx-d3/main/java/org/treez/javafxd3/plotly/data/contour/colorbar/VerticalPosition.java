package org.treez.javafxd3.plotly.data.contour.colorbar;



import java.util.ArrayList;
import java.util.List;

public enum VerticalPosition {

	//#region VALUES

	TOP("top"), //
	MIDDLE("middle"), //
	BOTTOM("bottom");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	VerticalPosition(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static VerticalPosition fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (VerticalPosition enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
