package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.scales.LinearScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class BarChartYDataFunction implements DataFunction<Double> {
	
	private WebEngine webEngine;
	private BarChart barChart;
	
	public BarChartYDataFunction(WebEngine webEngine, BarChart barChart){
		this.webEngine=webEngine;
		this.barChart = barChart;
	}

		@Override
		public Double apply(final Object context, final Object d, final int index) {

			JSObject jsDatum = (JSObject) d;
			Value value = new Value(webEngine, jsDatum);
			BarChartData data = value.<BarChartData> as();
			Double frequency = data.getFrequency();			
			
			LinearScale y = barChart.getYScale();
			return y.apply(frequency).asDouble();
		}	

}
