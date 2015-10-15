package com.github.javafxd3.demo.client.democases.behaviors;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.behaviour.Zoom;
import com.github.javafxd3.api.behaviour.Zoom.ZoomEventType;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.LinearScale;
import com.github.javafxd3.api.svg.Axis;
import com.github.javafxd3.api.svg.Axis.Orientation;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoFactory;
import com.github.javafxd3.demo.client.democases.Margin;

import javafx.scene.layout.VBox;

/**
 * This demo is a replica of Mike Bostock's
 * <a href="http://bl.ocks.org/mbostock/3892919">Pan-Zoom demo</a>
 *
 * 
 *         <br />
 *         <a href="https://github.com/augbog">Augustus Yuan</a>
 *
 */
public class ZoomDemo extends AbstractDemoCase {

	// #region ATTRIBUTES

	private Selection svg;
	private Selection scaleLabel;
	private Selection translateLabel;
	private Selection g;
	private Axis xAxis;
	private Axis yAxis;

	// set margins
	final Margin margin = new Margin(20, 20, 30, 40);
	final int width = 960 - margin.left - margin.right;
	final int height = 500 - margin.top - margin.bottom;

	// #end region

	// #region CONSTRUCTORS

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
			public ZoomDemo newInstance() {
				return new ZoomDemo(d3, demoPreferenceBox);
			}
		};
	}

	private void init() {

		LinearScale x = d3.scale().linear().domain(-width / 2, width / 2).range(0.0, width);

		LinearScale y = d3.scale().linear().domain(-height / 2, height / 2).range(height, 0.0);

		// set the x axis
		xAxis = d3.svg().axis().scale(x).orient(Orientation.BOTTOM).tickSize(-height);

		// set the y axis
		yAxis = d3.svg().axis().scale(y).orient(Orientation.LEFT).ticks(5).tickSize(-width);

		// create zoom behavior
		Zoom zoom = d3.behavior().zoom().x(x).y(y).on(ZoomEventType.ZOOM, new OnZoom());

		Selection selection = d3.select("root");

		// create text box
		scaleLabel = selection.append("div").text("scale:");
		translateLabel = selection.append("div").text("translate:");

		svg = selection.append("svg:svg").attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom).attr("class", "zoom");

		// calling zoom on group element
		g = svg.append("g").attr("class", "zoom").call(zoom);

		// append rectangle with class zoom
		// see D3Demo.css to see styling applied
		g.append("rect").attr("width", width).attr("height", height).attr("class", "zoom");

		g.append("g").attr("class", "zoom x axis").attr("transform", "translate(0," + height + ")").call(xAxis);

		g.append("g").attr("class", "zoom y axis").call(yAxis);

	}

	public class OnZoom implements DatumFunction<Void> {

		@Override
		public Void apply(final Object context, final Object d, final int index) {
			
			Value datum = (Value) d;						
			Element element =(Element) context;
			

			// print the current scale and translate
			scaleLabel.text("scale:" + d3.zoomEvent().scale());
			translateLabel.text("translate:" + d3.zoomEvent().translate());

			// apply zoom and panning on x and y axes
			g.select(".zoom.x.axis").call(xAxis);
			g.select(".zoom.y.axis").call(yAxis);

			return null;

		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.javafxd3.demo.client.DemoCase#start()
	 */
	@Override
	public void start() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.javafxd3.demo.client.DemoCase#stop()
	 */
	@Override
	public void stop() {

	}

	// #end region

}