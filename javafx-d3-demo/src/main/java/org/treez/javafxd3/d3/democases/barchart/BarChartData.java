package org.treez.javafxd3.d3.democases.barchart;

public class BarChartData {

	//#region ATTRIBUTES

	private String letter;
	private double frequency;

	//#end region

	//#region CONSTRUCTORS

	public BarChartData(final String letter, final double frequency) {
		super();

		this.letter = letter;
		this.frequency = frequency;
	}

	//#end region

	//#region ACCESSORS

	public String getLetter() {
		return letter;
	}

	public double getFrequency() {
		return frequency;
	}

	//#end region

}
