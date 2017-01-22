package org.treez.javafxd3.d3.democases.xy;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;

import javafx.scene.layout.VBox;



/**
 * Original example at http://stackoverflow.com/questions/10440646/a-simple-scatterplot-example-in-d3-js
 * 
 */
public class XyDemo extends AbstractDemoCase {
	
	//#region CONSTRUCTORS

	/**
	 * 
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public XyDemo(D3 d3, VBox demoPreferenceBox) {
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
				return new XyDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

				
		// data that you want to plot, I"ve used separate arrays for x and y values
		double[] xData = {5, 10, 15, 20};
		double[] yData = {3, 17, 4, 6};

		// size and margins for the chart		
		
		double totalWidth = 500;
		double totalHeight = 500;
		
		double marginLeft = 60;
		double marginRight = 15;
		
		double marginTop = 20;
		double marginBottom = 60;
		
		double width = totalWidth - marginLeft - marginRight;
		double height = totalHeight - marginTop - marginBottom;
		
		// x and y scales, I"ve used linear here but there are other options
		// the scales translate data values to pixel values for you
		double xMax = 22;
		LinearScale x = d3.scale().linear() //
		          .domain(new double[]{0, xMax})  // the range of the values to plot
		          .range(new double[]{0, width});        // the pixel range of the x-axis

		double yMax = 22;
		LinearScale y = d3.scale().linear() //
		          .domain(new double[]{0, yMax}) //
		          .range(new double[]{height, 0});

		// the chart object, includes all margins
		Selection chart = d3.select("svg") //
		.attr("width", width + marginRight + marginLeft) //
		.attr("height", height + marginTop + marginBottom) //
		.attr("class", "chart");

		// the main object where the chart and axis will be drawn
		Selection main = chart.append("g") //
		.attr("transform", "translate(" + marginLeft + "," + marginTop + ")") //
		.attr("width", width) //
		.attr("height", height) //
		.attr("class", "main");   

		// draw the x axis
		Axis xAxis = d3.svg().axis().scale(x).orient(Orientation.BOTTOM);

		main.append("g") //
		.attr("transform", "translate(0," + height + ")") //
		.attr("class", "main axis date").call(xAxis);

		// draw the y axis
		Axis yAxis = d3.svg().axis() //
		.scale(y) //
		.orient(Orientation.LEFT);

		main.append("g") //
		.attr("transform", "translate(0,0)") //
		.attr("class", "main axis date") //
		.call(yAxis);

		// draw the graph object
		Selection g = main.append("svg:g"); 

		g.selectAll("scatter-dots")
		  .data(yData)  // using the values in the ydata array
		  .enter().append("svg:circle")  // create a new circle for each value
		      .attr("cy", new YAxisDataFunction(engine, y) ) // translate y value to a pixel
		      .attr("cx", new XAxisDataFunction(x, xData)) // translate x value
		      .attr("r", 5) // radius of circle
		      .style("opacity", 0.6); // opacity of circle
	}

	@Override
	public void stop() {
	}

	//#end region	

}
