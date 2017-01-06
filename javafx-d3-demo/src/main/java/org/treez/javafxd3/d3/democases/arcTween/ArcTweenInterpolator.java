package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.interpolators.CallableInterpolator;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.interpolators.Interpolators;
import org.treez.javafxd3.d3.svg.Arc;

import javafx.scene.web.WebEngine;

public class ArcTweenInterpolator extends CallableInterpolator<String> {

	private ArcTweenFunction arcTweenFunction;
	private Interpolator<Double> interpolator;

	public ArcTweenInterpolator(WebEngine webEngine, ArcTweenFunction arcTweenFunction) {
		super(webEngine);
		this.arcTweenFunction = arcTweenFunction;
		interpolator = Interpolators.interpolateNumber(webEngine, arcTweenFunction.getEndAngle(), arcTweenFunction.getNewAngle());
	}	

	@Override
	public String interpolate(Object t) {

		double interpolated = interpolator.interpolate(t);
		
		Arc arcDatum = arcTweenFunction.getArcDatum();
		arcDatum.endAngle(interpolated);
		Arc arc = arcTweenFunction.getArc();
		return arc.generate(arcDatum);
	}

}
