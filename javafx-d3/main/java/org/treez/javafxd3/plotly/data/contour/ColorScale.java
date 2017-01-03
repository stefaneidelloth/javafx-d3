package org.treez.javafxd3.plotly.data.contour;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.javafx.EnumValueProvider;

public enum ColorScale implements EnumValueProvider<ColorScale> {

	//#region VALUES

	BLACKBODY("Blackbody"), //
	BLUERED("Bluered"), //
	EARTH("Earth"), //
	ELECTRIC("Electric"), //
	GREENS("Greens"), //
	GREYS("Greys"), //
	HOT("Hot"), //
	JET("Jet"), //
	PICNIC("Picnic"), //
	PORTLAND("Portland"), //
	RDBU("RdBu"), //
	YIGNBU("YIGnBu"), //
	YIORD("YIOrRd");

	//#end region

	//#region ATTRIBUTES

	String value;

	//#end region

	//#region CONSTRUCTORS

	ColorScale(String value) {
		this.value = value;
	}

	//#end region

	//#region METHODS

	@Override
	public String toString() {
		return value;
	}

	@Override
	public ColorScale fromString(final String value) {
		return valueOf(value.toUpperCase().replace("-", "_"));
	}

	@Override
	public List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (ColorScale enumValue : values()) {
			String stringValue = enumValue.value;
			values.add(stringValue);
		}
		return values;
	}

	//#end region

}
