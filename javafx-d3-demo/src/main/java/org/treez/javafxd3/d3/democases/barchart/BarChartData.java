package org.treez.javafxd3.d3.democases.barchart;

public class BarChartData {

	private final String letter;
	private final double frequency;

	public BarChartData(final String letter, final double frequency) {
		super();

		this.letter = letter;
		this.frequency = frequency;
	}

	public String getLetter() {
		return letter;
	}

	public double getFrequency() {
		return frequency;
	}

}
