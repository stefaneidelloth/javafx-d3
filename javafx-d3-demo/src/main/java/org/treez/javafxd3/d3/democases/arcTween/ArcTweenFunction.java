package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.svg.Arc;
import org.treez.javafxd3.d3.tweens.TweenFunction;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class ArcTweenFunction implements TweenFunction<String> {

	private JsEngine engine;
	private ArcTween arcTween;
	private double endAngle;
	private double newAngle;
	private Arc arcDatum;

	public ArcTweenFunction(JsEngine engine, ArcTween arcTween) {
		this.engine = engine;
		this.arcTween = arcTween;
	}

	@Override
	public JsObject apply(final Object context, final Object datum, final int index,
			final Object currentAttributeValue) {
		
		try {			
			
			arcDatum = ConversionUtil.convertObjectTo(datum,  Arc.class, engine);	
			
			endAngle = arcDatum.endAngle();			
			newAngle = arcTween.getNewAngle();
			
			Interpolator<String> interpolator =  new ArcTweenInterpolator(engine, this);
			
			return interpolator.asJsFunction();
			
			
		} catch (Exception e) {
			System.out.println("Error during transition: " + e.getMessage());
			throw new IllegalStateException("Error during transition", e);
		}
	}

	public double getEndAngle() {
		return endAngle;
	}

	public double getNewAngle() {
		return newAngle;
	}

	public Arc getArcDatum() {
		return arcDatum;
	}
	
	public Arc getArc(){
		return arcTween.getArc();
	}

}
