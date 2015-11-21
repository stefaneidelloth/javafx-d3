package com.github.javafxd3.demo.client.democases.svg.brush;

import java.util.ArrayList;
import java.util.List;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.scales.OrdinalScale;
import com.github.javafxd3.api.svg.Axis.Orientation;
import com.github.javafxd3.api.svg.Brush;
import com.github.javafxd3.api.svg.Brush.BrushEvent;
import com.github.javafxd3.api.svg.Symbol;
import com.github.javafxd3.api.svg.Symbol.Type;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;
import com.github.javafxd3.demo.client.democases.Margin;

import javafx.scene.layout.VBox;

/**
 * 
 */
public class OrdinalBrushingDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Selection symbol;
	private OrdinalScale x;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public OrdinalBrushingDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// this.css = Bundle.INSTANCE.css(); @Source("OrdinalBrushingDemo.css")
		// ob, selecting, selected, axis, brush

		//this.addStyleName("ob");
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * @param d3 
	 * @param demoPreferenceBox 
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new OrdinalBrushingDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		Type[] data = Symbol.Type.values();

		Margin margin = new Margin(210, 10, 210, 10);
		int width = 960 - margin.right - margin.left;
		final int height = 500 - margin.top - margin.bottom;

		List<String> stringList = new ArrayList<>();
		for(Type dataValue:data){
			stringList.add(dataValue.getValue());
		}
		String[] array = stringList.toArray(new String[data.length]);
		
		x = d3.scale().ordinal().domain(array).rangePoints(0, width, 1);

		svg = d3.select("root").append("svg").attr("width", width + margin.right + margin.left)
				.attr("height", height + margin.top + margin.bottom).append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		svg.append("g").attr("class", "x " + "axis").attr("transform", "translate(0," + height + ")")
				.call(d3.svg().axis().scale(x).orient(Orientation.BOTTOM));

		symbol = svg.append("g").selectAll("path").data(data).enter().append("path")
				.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;
						
						String value = datum.<Type> as().getValue();
						return "translate(" + x.apply(value).asDouble() + "," + (height / 2) + ")";
					}
				}).attr("d", d3.svg().symbol().type(new DatumFunction<Type>() {
					@Override
					public Type apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;
						
						return datum.<Type> as();
					}
				}).size(200));

		svg.append("g").attr("class", "brush")
				.call(d3.svg().brush().x(x).on(BrushEvent.BRUSH_START, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						brushstart();
						return null;
					}
				}).on(BrushEvent.BRUSH, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						brushmove();
						return null;
					}
				}).on(BrushEvent.BRUSH_END, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						brushend();
						return null;
					}
				})).selectAll("rect").attr("height", height);
	}

	@Override
	public void stop() {

	}

	private void brushstart() {
		svg.classed("selecting", true);
	}

	private void brushmove() {
		final Array<Double> extent = d3.event().getEventTarget().<Brush> cast().extent();
		symbol.classed("selected", new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;
				
				double value = x.apply(datum.<Type> as().getValue()).asDouble();
				return extent.get(0, Double.class) <= value && value <= extent.get(1, Double.class);
			}
		});
	}

	private void brushend() {
		svg.classed("selecting", !(d3.event().getEventTarget().<Brush> cast()).empty());
	}

	//#end region
}
