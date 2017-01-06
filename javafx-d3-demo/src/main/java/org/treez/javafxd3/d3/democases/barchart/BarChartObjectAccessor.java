package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class BarChartObjectAccessor implements DsvObjectAccessor<BarChartData> {
	
	private WebEngine webEngine;	
	
	public BarChartObjectAccessor(WebEngine webEngine){
		this.webEngine=webEngine;
		
	}

		@Override
		public BarChartData apply(final Object row, final int index) {

			JSObject jsRow = (JSObject) row;
			DsvRow dsvRow = new DsvRow(webEngine, jsRow);
			String letter = dsvRow.get("letter").asString();
			Double frequency = dsvRow.get("frequency").asDouble();

			return new BarChartData(letter, frequency);
		}		
	

}
