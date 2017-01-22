package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.Arc;

import org.treez.javafxd3.d3.core.JsEngine;

public class ArcTweenDataFunction implements DataFunction<String> {

	private JsEngine engine;
	private ArcTween arcTween;

	public ArcTweenDataFunction(JsEngine engine, ArcTween arcTween) {
		this.engine = engine;
		this.arcTween = arcTween;

	}

	@Override
	public String apply(Object context, Object datum, int index) {		
	
		Arc oldArc = ConversionUtil.convertObjectTo(datum,  Arc.class, engine);		
		
		Arc newArc = Arc.copy(engine, oldArc) //
				.endAngle(arcTween.getNewAngle());
		
		Arc arc = arcTween.getArc();
		
		Array<Double> point = arc.centroid(newArc, index);
		Double x = point.get(0, Double.class);
		Double y = point.get(1,  Double.class);
		
		int textWidth = arcTween.getTextWidth();
		String translateCommand =  "translate(" + (x - textWidth / 2) + "," + y + ")";
		return translateCommand;
	}

}
