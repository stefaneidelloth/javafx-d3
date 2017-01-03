package org.treez.javafxd3.plotly.data.contour.colorbar;



import java.util.ArrayList;
import java.util.List;

public enum HorizontalPosition {

	//#region VALUES

	LEFT("left"), //
	MIDDLE("middle"), //
	RIGHT("right");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	HorizontalPosition(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static HorizontalPosition fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (HorizontalPosition enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
