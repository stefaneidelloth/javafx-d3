package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.scales.OrdinalScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class BarChartXDatumFunction implements DatumFunction<Double> {
	
	private WebEngine webEngine;
	private BarChart barChart;
	
	public BarChartXDatumFunction(WebEngine webEngine, BarChart barChart){
		this.webEngine=webEngine;
		this.barChart = barChart;
	}

		@Override
		public Double apply(final Object context, final Object d, final int index) {

			JSObject jsDatum = (JSObject) d;
			Value value = new Value(webEngine, jsDatum);
			BarChartData data = value.as(BarChartData.class);
			String letter = data.getLetter();			
			
			OrdinalScale x = barChart.getXScale();
			return x.apply(letter).asDouble();
		}
	

}
