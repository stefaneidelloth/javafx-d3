
package com.github.javafxd3.demo.client.democases.geom;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.TimerFunction;
import com.github.javafxd3.api.geom.Quadtree.Callback;
import com.github.javafxd3.api.geom.Quadtree.Node;
import com.github.javafxd3.api.geom.Quadtree.RootNode;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;
import com.github.javafxd3.demo.client.democases.Margin;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class MitchellBestCandidate extends AbstractDemoCase {

	// #region ATTRIBUTES

	private boolean done = false;

	int maxRadius = 32; // maximum radius of circle
	int padding = 1; // padding between circles; also minimum radius
	Margin margin = new Margin(-maxRadius, -maxRadius, -maxRadius, -maxRadius);
	int width = 960 - margin.left - margin.right, height = 500 - margin.top - margin.bottom;
	private Selection svg;

	double k = 1; // initial number of candidates to consider per circle
	double m = 10; // initial number of circles to add per frame
	double n = 2500; // remaining number of circles to add
	private TimerFunction timerFunction;

	// #end region

	// #region CONSTRUTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public MitchellBestCandidate(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	// #end region

	// #region METHODS

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
				return new MitchellBestCandidate(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		final CircleGenerator newCircle = bestCircleGenerator(maxRadius, padding);

		svg = d3.select("root").append("svg").attr("width", width).attr("height", height).append("g").attr("transform",
				"translate(" + margin.left + "," + margin.top + ")");

		timerFunction = new TimerFunction() {

			@Override
			public boolean execute() {
				for (int i = 0; (i < m) && (--n >= 0); ++i) {
					Circle circle = newCircle.generate(k);

					svg.append("circle").attr("cx", circle.x).attr("cy", circle.y).attr("r", 0)
							.style("fill-opacity", (Math.random() + .5) / 2).transition().attr("r", circle.r);

					// As we add more circles, generate more candidates per
					// circle.
					// Since this takes more effort, gradually reduce circles
					// per frame.
					if (k < 500) {
						k *= 1.01;
						m *= .998;
					}
				}
				return n == 0 || done;
			}
		};
		done = false;
		d3.timer(timerFunction);

	}

	@Override
	public void stop() {
		done = true;
	}

	private CircleGenerator bestCircleGenerator(final double maxRadius, final double padding) {

		final RootNode<Circle> quadtree = d3.geom().quadtree().x(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d, final int index) {
				
				Element element = (Element) context;
				Value datum = (Value) d;
				
				return datum.<Circle> as().x;
			}
		}).y(new DatumFunction<Double>() {
			@Override
			public Double apply(final Object context, final Object d, final int index) {
				
				Element element = (Element) context;
				Value datum = (Value) d;
				
				return datum.<Circle> as().y;
			}
		}).extent(0, 0, width, height).apply(new Circle[1]);

		return new CircleGenerator() {

			// #region ATTRIBUTES

			private double minDistance;
			double searchRadius = maxRadius * 2;
			double maxRadius2 = maxRadius * maxRadius;
			double bestX, bestY, bestDistance = 0;

			// #end region

			// #region METHODS

			@Override
			public Circle generate(final double k) {
				bestX = bestY = bestDistance = 0;

				for (int i = 0; (i < k) || (bestDistance < padding); ++i) {

					final double x = (Math.random() * width);
					final double y = (Math.random() * height);
					final double rx1 = x - searchRadius;
					final double rx2 = x + searchRadius;
					final double ry1 = y - searchRadius;
					final double ry2 = y + searchRadius;

					minDistance = maxRadius; // minimum distance for this
												// candidate

					quadtree.visit(new Callback<Circle>() {
						@Override
						public boolean visit(final Node<Circle> quad,
								final double x1, final double y1, final double x2, final double y2) {

							Circle p = quad.point();
							if (p != null) {
								double dx = x - p.x;
								double dy = y - p.y;
								double d2 = (dx * dx) + (dy * dy);
								double r2 = p.r * p.r;
								if (d2 < r2) {
									minDistance = 0;
									return true;
								}
								// within a circle
								double d = (Math.sqrt(d2) - p.r);
								if (d < minDistance) {
									minDistance = d;
								}
							}
							// outside
							// search
							// radius
							return (minDistance == 0) || (x1 > rx2) || (x2 < rx1) || (y1 > ry2) || (y2 < ry1); // or
						}

					});

					if (minDistance > bestDistance) {
						bestX = x;
						bestY = y;
						bestDistance = minDistance;
					}
				}

				Circle best = new Circle(bestX, bestY, bestDistance - padding);
				quadtree.add(best);
				return best;
			}

			// #end region
		};
	}

	// #end region CLASSES & INTERFACES

	private static interface CircleGenerator {
		public Circle generate(double k);
	}

	private static class Circle {
		double x, y, r;

		public Circle(final double x, final double y, final double r) {
			super();
			this.x = x;
			this.y = y;
			this.r = r;
		}

	}

	// #end region

}
