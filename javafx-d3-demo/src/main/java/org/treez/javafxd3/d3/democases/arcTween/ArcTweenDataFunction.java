package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.svg.Arc;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class ArcTweenDataFunction implements DataFunction<String> {

	private WebEngine webEngine;
	private ArcTween arcTween;

	public ArcTweenDataFunction(WebEngine webEngine, ArcTween arcTween) {
		this.webEngine = webEngine;
		this.arcTween = arcTween;

	}

	@Override
	public String apply(Object context, Object datum, int index) {
		
		
		JSObject jsDatum = (JSObject) datum;
		Value value = new Value(webEngine, jsDatum);
		
		JSObject jsArc = value.as();
		Arc oldArc = new Arc(webEngine, jsArc);		
		
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
