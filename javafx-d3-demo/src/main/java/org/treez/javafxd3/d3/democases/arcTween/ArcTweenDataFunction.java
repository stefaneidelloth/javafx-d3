package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.Arc;

import javafx.scene.web.WebEngine;

public class ArcTweenDataFunction implements DataFunction<String> {

	private WebEngine webEngine;
	private ArcTween arcTween;

	public ArcTweenDataFunction(WebEngine webEngine, ArcTween arcTween) {
		this.webEngine = webEngine;
		this.arcTween = arcTween;

	}

	@Override
	public String apply(Object context, Object datum, int index) {		
	
		Arc oldArc = ConversionUtil.convertObjectTo(datum,  Arc.class, webEngine);		
		
		Arc newArc = Arc.copy(webEngine, oldArc) //
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
