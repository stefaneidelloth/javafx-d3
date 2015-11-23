
package com.github.javafxd3.demo.client.democases.geom.mitchell;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.functions.TimerFunction;
import com.github.javafxd3.api.geom.Quadtree.RootNode;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;
import com.github.javafxd3.demo.client.democases.Margin;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class MitchellBestCandidate extends AbstractDemoCase {

	//#region ATTRIBUTES

	private boolean done = false;

	private int maxRadius = 22; // maximum radius of circle
	private int padding = 1; // padding between circles; also minimum radius
	private Margin margin = new Margin(-maxRadius, -maxRadius, -maxRadius, -maxRadius);
	private int width = 700 - margin.left - margin.right;
	private int height = 400 - margin.top - margin.bottom;
	private Selection svg;

	private TimerFunction timerFunction;

	//#end region

	//#region CONSTRUTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public MitchellBestCandidate(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
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
				return new MitchellBestCandidate(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		final CircleGenerator newCircle = createBestCircleGenerator(maxRadius, padding);

		svg = d3.select("svg") //				
				.attr("width", width) //
				.attr("height", height) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		timerFunction = new MitchellTimerFunction(newCircle, svg, this);
		done = false;
		d3.timer(timerFunction);

	}

	@Override
	public void stop() {
		done = true;
	}

	private CircleGenerator createBestCircleGenerator(final double maxRadius, final double padding) {

		final RootNode<Circle> quadtree = d3.geom() //
				.quadtree() //
				.x(new XDatumFunction(webEngine)) //
				.y(new XDatumFunction(webEngine)) //
				.extent(0, 0, width, height) //
				.apply(new Circle[1]);

		return new MitchellCircleGenerator(quadtree, maxRadius, width, height, padding) ;
	}
	
	//#end region
	
	//#region ACCESSORS
	
	public boolean getDone() {
		return done;
	}
	
	//#end region

}
