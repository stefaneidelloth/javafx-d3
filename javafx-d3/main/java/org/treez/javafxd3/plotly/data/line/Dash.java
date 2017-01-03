package org.treez.javafxd3.plotly.data.line;

import java.util.ArrayList;
import java.util.List;

public enum Dash {

	//#region VALUES

	SOLID("solid"), //
	DOT("dot"), //
	DASH("dash"), //
	LONGDASH("longdash"), //
	DASHDOT("dashdot"), //
	LONGDASHDOT("longdashdot");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	Dash(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	public static Dash fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (Dash enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
