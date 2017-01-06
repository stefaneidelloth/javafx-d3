package org.treez.javafxd3.d3.democases;

import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.svg.Arc;
import org.treez.javafxd3.d3.tweens.TweenFunction;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class ArcTweenFunction implements TweenFunction<String> {

	private WebEngine webEngine;
	private ArcTween arcTween;
	private double endAngle;
	private double newAngle;
	private Arc arcDatum;

	public ArcTweenFunction(WebEngine webEngine, ArcTween arcTween) {
		this.webEngine = webEngine;
		this.arcTween = arcTween;
	}

	@Override
	public JSObject apply(final Object context, final Object datum, final int index,
			final Object currentAttributeValue) {
		
		try {
			
			JSObject jsDatum = (JSObject) datum;			
			Value value = new Value(webEngine, jsDatum);			
			JSObject arcObject = value.as();			
			arcDatum = new Arc(webEngine, arcObject);
			
			endAngle = arcDatum.endAngle();			
			newAngle = arcTween.getNewAngle();
			
			Interpolator<String> interpolator =  new ArcTweenInterpolator(webEngine, this);
			
			return interpolator.asJSOFunction();
			
			
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
