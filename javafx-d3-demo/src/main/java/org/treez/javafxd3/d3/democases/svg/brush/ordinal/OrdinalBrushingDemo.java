package org.treez.javafxd3.d3.democases.svg.brush.ordinal;

import java.util.ArrayList;
import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.JsObject;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.demo.Margin;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.svg.Symbol;
import org.treez.javafxd3.d3.svg.SymbolType;

import javafx.scene.layout.VBox;

public class OrdinalBrushingDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Selection svg;
	private Selection symbol;
	private OrdinalScale x;

	//#end region

	//#region CONSTRUCTORS

	public OrdinalBrushingDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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
		for (SymbolType dataValue : data) {
			stringList.add(dataValue.toString());
		}
		String[] array = stringList.toArray(new String[data.length]);

		x = d3.scale() //
				.ordinal() //
				.domain(array) //
				.rangePoints(0, width, 1);

		svg = d3.select("#svg") //				
				.attr("width", width + margin.right + margin.left) //
				.attr("height", height + margin.top + margin.bottom) //
				.append("g") //
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		Axis axis = d3.svg().axis() //
				.scale(x) //
				.orient(Orientation.BOTTOM);

		svg.append("g") //
				.attr("class", "x axis") //
				.attr("transform", "translate(0," + height + ")") //
				.call(axis);

		DataFunction<String> transformFunction = new DataFunctionWrapper<>(SymbolType.class, engine, (symbolType) -> {			
			Double scaledX = x.apply(symbolType) //
					.asDouble();
			
			String translation =  "translate(" + scaledX + "," + (height / 2) + ")";
			return translation;
		});

		DataFunction<SymbolType> symbolTypeFunction = new DataFunctionWrapper<>(SymbolType.class, engine,
				(symbolType) -> {
					return symbolType;
				});

		Symbol pathSymbol = d3.svg() //
				.symbol() //
				.type(symbolTypeFunction) //
				.size(200);

		symbol = svg.append("g") //
				.selectAll("path") //
				.data(data) //
				.enter() //
				.append("path") //
				.attr("transform", transformFunction) //
				.attr("d", pathSymbol);

		DataFunction<Void> brushStartFunction = new DataFunctionWrapper<>(() -> {
			brushStart();
		});

		DataFunction<Void> brushMoveFunction = new DataFunctionWrapper<>(() -> {
			brushMove();
		});

		DataFunction<Void> brushEndFunction = new DataFunctionWrapper<>(() -> {
			brushend();
		});

		Brush brush = d3.svg()//
				.brush() //
				.x(x) //
				.on(BrushEvent.BRUSH_START, brushStartFunction) //
				.on(BrushEvent.BRUSH, brushMoveFunction) //
				.on(BrushEvent.BRUSH_END, brushEndFunction);

		svg.append("g") //
				.attr("class", "brush") //
				.call(brush) //
				.selectAll("rect") //
				.attr("height", height);
	}

	@Override
	public void stop() {

	}

	private void brushStart() {
		svg.classed("selecting", true);
	}

	private void brushMove() {
		
	
		
		Selection target = d3.event().getEventTarget();
	
		JsObject jsBrush = target.getJsObject();
		Brush brush = new Brush(engine, jsBrush);

		final Array<Double> extent = brush.extent();

		DataFunction<Boolean> selectionDataFunction = new DataFunctionWrapper<>(SymbolType.class, engine,
				(symbolType) -> {
					
					double value = x.apply(symbolType).asDouble();
					return extent.get(0, Double.class) <= value && value <= extent.get(1, Double.class);
					
				});

		symbol.classed("selected", selectionDataFunction);

	}

	private void brushend() {

		Selection brush = d3.event().getEventTarget();
		svg.classed("selecting", !(brush).empty());

	}

	//#end region
}
