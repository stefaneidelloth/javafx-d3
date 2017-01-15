package org.treez.javafxd3.d3.democases.arcTween;

import java.util.Timer;
import java.util.TimerTask;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.svg.Arc;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 */
public class ArcTween extends AbstractDemoCase {

	//#region ATTRIBUTES

	private static final String INTRO_TEXT = "Demonstrate the SVG Arc API.";

	private Timer timer;
	private TimerTask timerTask;

	private Selection svg;
	private Arc arc;
	private Selection centroidText;
	private double newAngle;

	private int textWidth;

	//#end region

	//#region CONSTRUCTORS

	public ArcTween(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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
		int width = 960;
		int height = 500;

		final double TWO_PI = 2 * Math.PI; // http://tauday.com/tau-manifesto

		d3.select("#svg") //
				.attr("height", 40) //
				.append("text") //
				.attr("x", 30) //
				.attr("y", 30) //
				.text(INTRO_TEXT);

		// the arc function defines constants for inner and outer radius, and
		// start angle
		// but the end angle is deliberately kept undefined
		arc = d3.svg() //
				.arc() //
				.innerRadius(180) //
				.outerRadius(240) //
				.startAngle(0);

		// Create the SVG container, and apply a transform such that the origin
		// is the center of the canvas. This way, we don't need to position
		// arcs individually.
		svg = d3.select("#svg") //				
				.attr("width", width) //
				.attr("height", height) //
				.append("g") //
				.attr("transform", "translate(" + (width / 2) + "," + (height / 2) + ")");

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
				.datum(json) //
				.style("fill", "#ddd") //
				.attr("d", arc);

		// set the angle to 12.7%
		json.endAngle(.127 * TWO_PI);
		// Add the foreground arc in orange, currently showing 12.7%.
		final Selection foreground = svg //
				.append("path") //
				.datum(json) //
				.style("fill", "orange") //
				.attr("d", arc);

		centroidText = svg //
				.append("text") //
				.text("centroid") //
				.datum(json) //
				.style("fill", "white") //
				.style("stroke", "black") //
				.style("font-size", "30px");

		textWidth = getTextWidth(centroidText.node());
		// Every so often, start a transition to a new random angle. Use //
		// transition.call // (identical to selection.call) so that we can
		// encapsulate the logic for tweening the arc in a separate
		// function below.

		timer = new Timer();

		timerTask = new TimerTask() {

			@Override
			public void run() {

				Platform.runLater(() -> {

					Transition transition = foreground //
							.transition() //							
							.duration(750);

					newAngle = Math.random() * TWO_PI;
					doTransition(transition, newAngle);

					centroidText //
							.transition() //
							.duration(750) //
							.attr("transform", new ArcTweenDataFunction(webEngine, ArcTween.this));
				});

			}
		};

		timer.schedule(timerTask, 0, 1500);

	}

	private static int getTextWidth(Element e) {
		return (int) e.getBBox().getWidth();
	}

	public static interface TransitionFunction {
		public void apply(Transition t, Object... objects);
	}

	protected synchronized void doTransition(final Transition transition, final double newAngle) {
		transition.attrTween("d", new ArcTweenFunction(webEngine, ArcTween.this));
	}

	@Override
	public void stop() {

		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		arc = null;
	}

	public double getNewAngle() {
		return newAngle;
	}

	public Arc getArc() {
		return arc;
	}

	public int getTextWidth() {
		return textWidth;
	}

	//#end region

}
