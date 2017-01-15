package org.treez.javafxd3.d3.democases.arcTween;

import org.treez.javafxd3.d3.core.ConversionUtil;
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
						
			arcDatum = ConversionUtil.convertObjectTo(datum,  Arc.class, webEngine);	
			
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
