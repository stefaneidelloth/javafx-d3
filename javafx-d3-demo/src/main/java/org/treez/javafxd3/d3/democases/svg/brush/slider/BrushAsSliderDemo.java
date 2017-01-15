package org.treez.javafxd3.d3.democases.svg.brush.slider;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.demo.Margin;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.ContextDataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.Node;

import javafx.scene.layout.VBox;


public class BrushAsSliderDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Brush brush;

	private LinearScale x;

	private Selection handle;

	//#end region

	//#region CONSTRUCTORS

	public BrushAsSliderDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new BrushAsSliderDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		Margin margin = new Margin(200, 50, 200, 50);
		int width = 900 - margin.left - margin.right;
		int height = 600 - margin.bottom - margin.top;

		x = d3.scale()//
				.linear()//
				.domain(0, 180)//
				.range(0, width)//
				.clamp(true);

		DataFunction<Void> brushFunction = new ContextDataFunctionWrapper<>(webEngine, (element)->{
			brushed(element);
			return null;
		});		

		brush = d3.svg().brush()//
				.x(x)//
				.extent(0, 0)//
				.on(BrushEvent.BRUSH, brushFunction);

		Selection svg = d3.select("#svg")
				//.style("width", width + margin.left + margin.right + "px")			
				.attr("width", width + margin.left + margin.right) //
				.attr("height", height + margin.top + margin.bottom) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		Selection svg2 = svg.append("g")//
				.attr("class", "x axis") //
				.attr("transform", "translate(0," + height / 2 + ")");

		DataFunction<String> tickFormatFunction = new DataFunctionWrapper<>(String.class, webEngine, (value) -> {
			return value + "A";
		});

		Axis axis = d3.svg().axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM) //
				.tickFormat(tickFormatFunction) //
				.tickSize(0) //
				.tickPadding(12);

		DataFunction<Element> datumFunction = new ContextDataFunctionWrapper<>(webEngine, (element)->{
				Node cloneNode = element.cloneNode(true);
				element.getParentNode().appendChild(cloneNode);
				return cloneNode.cast(Element.class);			
		});

		svg2.call(axis) //
				.select(".domain") //
				.select(datumFunction) //
				.attr("class", "halo");

		Selection slider = svg.append("g") //
				.attr("class", "slider") //
				.call(brush);

		slider.selectAll(".extent,.resize") //
				.remove();

		slider.select(".background") //
				.attr("height", height);

		handle = slider.append("circle") //
				.attr("class", "handle") //
				.attr("transform", "translate(0," + height / 2 + ")") //
				.attr("r", 9);

		slider.call(brush.event()) //
				.transition() //			
				.duration(750) //
				.call(brush.extent(70, 70)) //
				.call(brush.event());

	}

	private void brushed(final Element context) {

		double value = brush.<Double> extent().get(0, Double.class);

		if (d3.event().sourceEvent() != null) { // not a programmatic event			
			Double xMouse = d3.mouse(context).get(0, Double.class);
			value = x.invert(xMouse).asDouble();
			brush.extent(value, value);
		}

		String cx = x.apply(value).asString();
		handle.attr("cx", cx);

		Colors colors = new Colors(webEngine);
		String color = colors.hsl((int) value, .8, .8).toHexaString();

		d3.select("#svg") //
				.style("background-color", color);
	}

	@Override
	public void stop() {

	}

	//#end region

}
