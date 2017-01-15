package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

import javafx.scene.web.WebEngine;

public class BarChartObjectAccessor implements DsvObjectAccessor<BarChartData> {
	
	private WebEngine webEngine;	
	
	public BarChartObjectAccessor(WebEngine webEngine){
		this.webEngine=webEngine;
		
	}

		@Override
		public BarChartData apply(final Object row, final int index) {
		
			DsvRow dsvRow = ConversionUtil.convertObjectTo(row, DsvRow.class, webEngine);
			
			String letter = dsvRow.get("letter").asString();
			Double frequency = dsvRow.get("frequency").asDouble();

			return new BarChartData(letter, frequency);
		}		
	

}
