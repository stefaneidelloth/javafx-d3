package org.treez.javafxd3.d3.democases.behaviors;

import org.treez.javafxd3.d3.behaviour.Zoom;
import org.treez.javafxd3.d3.behaviour.Zoom.ZoomEventType;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.democases.Margin;
import org.treez.javafxd3.d3.scales.LinearScale;

import javafx.scene.layout.VBox;

/**
 * This demo is a replica of Mike Bostock's
 * <a href="http://bl.ocks.org/mbostock/3892919">Pan-Zoom demo</a>
 *
 * 
 * <br />
 * <a href="https://github.com/augbog">Augustus Yuan</a>
 *
 */
public class ZoomDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Selection scaleLabel;
	private Selection translateLabel;	
	private Axis xAxis;
	private Axis yAxis;

	// set margins
	final Margin margin = new Margin(20, 20, 30, 40);
	final int width = 700 - margin.left - margin.right;
	final int height = 400 - margin.top - margin.bottom;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ZoomDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		init();
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
			public ZoomDemo newInstance() {
				return new ZoomDemo(d3, demoPreferenceBox);
			}
		};
	}

	private void init() {

		LinearScale x = d3.scale() //
				.linear() //
				.domain(-width / 2, width / 2) //
				.range(0.0, width);

		LinearScale y = d3.scale() //
				.linear() //
				.domain(-height / 2, height / 2) //
				.range(height, 0.0);

		// set the x axis
		xAxis = d3.svg() //
				.axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM) //
				.tickSize(-height);

		// set the y axis
		yAxis = d3.svg() //
				.axis() //
				.scale(y) //
				.orient(Orientation.LEFT) //
				.ticks(5) //
				.tickSize(-width);

		Selection selection = d3.select("#root");

		// create info text boxes
		scaleLabel = selection.append("div") //
				.text("scale:");

		translateLabel = selection //
				.append("div") //
				.text("translate:");

		//.x(x) //
		//.y(y) //

		svg = d3.select("#svg") //
				.attr("width", width + margin.left + margin.right) //
				.attr("height", height + margin.top + margin.bottom) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		// create zoom behavior
		Zoom zoom = d3.behavior() //
				.zoom() //	
				.x(x)
				.y(y)
				.on(ZoomEventType.ZOOM, new ZoomDatumFunction(d3, scaleLabel, translateLabel, svg, xAxis, yAxis));

		svg.call(zoom);

		svg.append("rect") //
				.attr("width", width) //
				.attr("height", height);

		svg.append("g") //
				.attr("class", "x axis") //
				.attr("transform", "translate(0," + height + ")") //
				.call(xAxis);

		svg.append("g") //
				.attr("class", "y axis") //
				.call(yAxis);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.treez.javafxd3.d3.DemoCase#start()
	 */
	@Override
	public void start() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.treez.javafxd3.d3.DemoCase#stop()
	 */
	@Override
	public void stop() {

	}

	//#end region

}