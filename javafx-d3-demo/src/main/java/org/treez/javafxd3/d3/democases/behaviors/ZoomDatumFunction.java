package org.treez.javafxd3.d3.democases.behaviors;

import org.treez.javafxd3.d3.svg.Axis;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.DatumFunction;

public class ZoomDatumFunction implements DatumFunction<Void> {
	
	
	private D3 d3;
	private Selection scaleLabel;
	private Selection translateLabel;	
	private Selection svg;
	private Axis xAxis;
	private Axis yAxis;
	
	public ZoomDatumFunction(
			D3 d3, 
			Selection scaleLabel, 
			Selection translateLabel, 
			Selection svg,
			Axis xAxis,
			Axis yAxis
			){
		this.d3 = d3;
		this.scaleLabel = scaleLabel;
		this.translateLabel = translateLabel;
		this.svg=svg;
		this.xAxis=xAxis;
		this.yAxis=yAxis;
	}

	@Override
	public Void apply(Object context, Object d, int index) {
		
		// print the current scale and translate
		scaleLabel.text("scale:" + d3.zoomEvent().scale());
		String translation = d3.zoomEvent().translate().toString();
		translateLabel.text("translate:" + translation);

		// apply zoom and panning on x and y axes		
		svg.select(".x.axis").call(xAxis);
		svg.select(".y.axis").call(yAxis);

		return null;

	}

}
