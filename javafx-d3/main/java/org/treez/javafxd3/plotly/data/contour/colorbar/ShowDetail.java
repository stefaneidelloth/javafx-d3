package org.treez.javafxd3.plotly.data.contour.colorbar;



import java.util.ArrayList;
import java.util.List;

public enum ShowDetail {

	//#region VALUES

	ALL("all"), //
	FIRST("first"), //
	LAST("last"), //
	NONE("none");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	ShowDetail(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static ShowDetail fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (ShowDetail enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
