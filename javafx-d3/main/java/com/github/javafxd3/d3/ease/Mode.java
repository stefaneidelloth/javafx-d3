package com.github.javafxd3.d3.ease;

/**
 *
 *
 */
public enum Mode {

	//#region VALUES

	/**
	 * The identity function
	 */
	IN("in"),

	/**
	 * reverses the easing direction [1,0]
	 */
	OUT("out"),

	/**
	 * copies and mirrors the easing function from [0,.5] and [.5,1].
	 */
	IN_OUT("in-out"),

	/**
	 * copies and mirrors the easing function from [1,.5] and [.5,0].
	 */
	OUT_IN("out-in");

	//#end region

	//#region ATTRIBUTES

	private String value;

	//#end region

	//#region CONSTRUCTORS

	private Mode(final String value) {
		this.value = value;
	}

	//#end region

	//#region ACCESSORS

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	//#end region
}
