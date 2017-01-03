package org.treez.javafxd3.d3.democases.svg.brush;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.democases.Margin;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.svg.SymbolType;

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
		SymbolType[] data = SymbolType.values();

		Margin margin = new Margin(210, 10, 210, 10);
		int width = 960 - margin.right - margin.left;
		final int height = 500 - margin.top - margin.bottom;

		List<String> stringList = new ArrayList<>();
		for(SymbolType dataValue:data){
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
						
						String value = datum.<SymbolType> as().getValue();
						return "translate(" + x.apply(value).asDouble() + "," + (height / 2) + ")";
					}
				}).attr("d", d3.svg().symbol().type(new DatumFunction<SymbolType>() {
					@Override
					public SymbolType apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;
						
						return datum.<SymbolType> as();
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
		Selection target = d3.event().getEventTarget();
		
		throw new IllegalStateException("not yet implemented");
		
		/*
		
		final Array<Double> extent = target.<Brush> cast().extent();
		symbol.classed("selected", new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;
				
				double value = x.apply(datum.<Type> as().getValue()).asDouble();
				return extent.get(0, Double.class) <= value && value <= extent.get(1, Double.class);
			}
		});
		
		*/
	}

	private void brushend() {
		
throw new IllegalStateException("not yet implemented");
		
		/*
		svg.classed("selecting", !(d3.event().getEventTarget().<Brush> cast()).empty());
		
		*/
	}

	//#end region
}
