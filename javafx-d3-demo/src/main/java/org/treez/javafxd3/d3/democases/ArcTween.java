package org.treez.javafxd3.d3.democases;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.interpolators.CallableInterpolator;
import org.treez.javafxd3.d3.interpolators.Interpolator;
import org.treez.javafxd3.d3.interpolators.Interpolators;
import org.treez.javafxd3.d3.svg.Arc;
import org.treez.javafxd3.d3.tweens.TweenFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import com.sun.glass.ui.Timer;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * 
 * 
 */
public class ArcTween extends AbstractDemoCase {

	//#region ATTRIBUTES

	private static final String INTRO_TEXT = "This demonstrate the SVG Arc API with Transition API: here, a complex transition is constructed from a custom interpolator. "
			+ "Moreover, the centroid() method of Arc is illustrated to show how easy it is to display labels in a donut chart.";
	private Timer timer;
	private Selection svg;
	private Arc arc;
	private Selection centroidText;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ArcTween(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// this.add(new Label(INTRO_TEXT));
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new ArcTween(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		int width = 960, height = 500;

		final double TWO_PI = 2 * Math.PI; // http://tauday.com/tau-manifesto

		// the arc function defines constants for inner and outer radius, and
		// start angle
		// but the end angle is deliberately kept undefined
		arc = d3.svg().arc().innerRadius(180).outerRadius(240).startAngle(0);

		// Create the SVG container, and apply a transform such that the origin
		// is the center of the canvas. This way, we don't need to position
		// arcs individually.
		svg = d3.select("root").append("svg").attr("width", width).attr("height", height).append("g").attr("transform",
				"translate(" + (width / 2) + "," + (height / 2) + ")");

		// construct a a stupid object containing the
		// property "endAngle" as a constant.

		Arc json = Arc.constantArc(webEngine).endAngle(TWO_PI);
		// Add the background arc, from 0 to 100%
		// Here, the path d attribute is filled using the arc function,
		// which will received in parameter the object passed to datum.
		// This function will used the default accessors of the Arc objects,
		// those accessors assuming that the data passed contains
		// attributes named as the accessors.
		svg.append("path")
				// pass a data representing a constant endAngle arc
				.datum(json).style("fill", "#ddd").attr("d", arc);

		// set the angle to 12.7%
		json.endAngle(.127 * TWO_PI);
		// Add the foreground arc in orange, currently showing 12.7%.
		final Selection foreground = svg.append("path").datum(json).style("fill", "orange").attr("d", arc);

		centroidText = svg.append("text").text("centroid").datum(json).style("fill", "white").style("stroke", "black")
				.style("font-size", "30px");
		final int textWidth = getTextWidth(centroidText.node());
		// Every so often, start a transition to a new random angle. Use //
		// transition.call // (identical to selection.call) so that we can
		// encapsulate the logic // for // tweening the arc in a separate
		// function
		// below.

		// TODO
		/*
		 * timer = new Timer() {
		 * 
		 * @Override public void run() { Transition transition =
		 * foreground.transition().duration(750); final double newAngle =
		 * Math.random() * TWO_PI; doTransition(transition, newAngle);
		 * centroidText.transition().duration(750).attr("transform", new
		 * DatumFunction<String>() {
		 * 
		 * @Override public String apply(Element context, Value d, int index) {
		 * Arc newArc = Arc.copy(d.<Arc> as()).endAngle(newAngle); Double[]
		 * point = arc.centroid(newArc, index); return "translate(" + (point[0]
		 * - textWidth / 2) + "," + point[1] + ")"; } }); } };
		 * timer.scheduleRepeating(1500);
		 */

	}

	private static int getTextWidth(Element e) {
		return (int) e.getBBox().getWidth();
	}

	public static interface TransitionFunction {
		public void apply(Transition t, Object... objects);
	}

	/**
	 * @param transition
	 * @param d
	 */
	protected void doTransition(final Transition transition, final double newAngle) {

		transition.attrTween("d", new TweenFunction<String>() {
			@Override
			public Interpolator<String> apply(final Element context, final Value datum, final int index,
					final Value currentAttributeValue) {
				try {
					final Arc arcDatum = datum.as();
					final double endAngle = arcDatum.endAngle();
					return new CallableInterpolator<String>() {
						private final Interpolator<Double> interpolator = Interpolators.interpolateNumber(endAngle,
								newAngle);

						@Override
						public String interpolate(final double t) {
							double interpolated = interpolator.interpolate(t);
							arcDatum.endAngle(interpolated);
							return arc.generate(arcDatum);
						}
					};
				} catch (Exception e) {
					System.out.println("Error during transition" + e.getMessage());
					throw new IllegalStateException("Error during transition", e);
				}
			}
		});

		// transition.attrTween("d", "blah");
	}

	@Override
	public void stop() {

		/*
		 * TODO if (timer != null) { timer.cancel(); timer = null; }
		 */
		arc = null;

	}

	//#end region

}
