package org.treez.javafxd3.d3.democases.barchart;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.dsv.DsvObjectAccessor;
import org.treez.javafxd3.d3.dsv.DsvRow;

public class BarChartObjectAccessor implements DsvObjectAccessor<BarChartData> {
	
	private JsEngine engine;	
	
	public BarChartObjectAccessor(JsEngine engine){
		this.engine=engine;
		
	}

		@Override
		public BarChartData apply(final Object datum, final int index) {		
			
			DsvRow dsvRow = ConversionUtil.convertObjectTo(datum, DsvRow.class, engine);
			
			String letter = dsvRow.get("letter").asString();
			Double frequency = dsvRow.get("frequency").asDouble();

			return new BarChartData(letter, frequency);
		}		
	

}
