package org.treez.javafxd3.d3.democases.chorddiagram;

import java.util.List;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.Arrays;
import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.IndexDataFunctionWrapper;
import org.treez.javafxd3.d3.layout.Chord;
import org.treez.javafxd3.d3.layout.Chord.ChordItem;
import org.treez.javafxd3.d3.layout.Chord.Group;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Arc;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.layout.VBox;
import netscape.javascript.JSObject;

public class ChordDiagram extends AbstractDemoCase {

	//#region CONSTRUCTORS

	public ChordDiagram(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new ChordDiagram(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		// From http://mkweb.bcgsc.ca/circos/guide/tables/

		Double[][] matrix = matrix();

		Chord chord = d3.layout() //
				.chord() //
				.padding(.05) //
				.sortSubgroups(Arrays.descending(webEngine)) //
				.matrix(matrix);

		int width = 900;
		int height = 500;
		double innerRadius = Math.min(width, height) * .35;
		final double outerRadius = innerRadius * 1.1;

		final OrdinalScale fillScale = d3.scale() //
				.ordinal() //
				.domain(Arrays.range(4, webEngine)) //
				.range("#000000", "#FFDD89", "#957244", "#F26223");

		final Selection svg = d3.select("#svg") //			
				.attr("width", width) //
				.attr("height", height) //
				.append("g") //
				.attr("transform", "translate(" + (width / 2) + "," + (height / 2) + ")");

		DataFunction<String> fillFunction = new DataFunctionWrapper<>(Group.class, webEngine, (group) -> {
			int i = group.index();
			Value scaledValue = fillScale.apply(i);
			String fillString = scaledValue.asString();
			return fillString;
		});

		Arc arc = d3.svg().arc() //
				.innerRadius(innerRadius)//
				.outerRadius(outerRadius);

		List<Group> chordGroups = chord.groups();

		svg.append("g")//
				.selectAll("path")//
				.data(chordGroups)//
				.enter()//
				.append("path")//
				.style("fill", fillFunction) //
				.style("stroke", fillFunction) //
				.attr("d", arc) //
				.on("mouseover", fade(svg, .1)) //
				.on("mouseout", fade(svg, 1));

		// Returns an array of tick angles and labels, given a group.
		DataFunction<GroupTick[]> groupTicksFunction = new DataFunctionWrapper<>(Group.class, webEngine, (group) -> {

			final double k = (group.endAngle() - group.startAngle()) / group.value();

			Array<Double> range = Arrays.range(0, group.value(), 1000, webEngine);

			GroupTick[] groupTicks = new GroupTick[range.length()];

			Double startAngle = group.startAngle();

			int[] index = { 0 };
			range.forEach((object) -> {

				Double value = ConversionUtil.convertObjectTo(object, Double.class, webEngine);
				double angle = (value * k) + startAngle;
				String label = (index[0] % 5) != 0 ? "" : (value / 1000) + "k";
				groupTicks[index[0]] = GroupTick.create(angle, label, webEngine);
				index[0]++;
			});

			return groupTicks;
		});

		DataFunction<String> transformGroupFunction = new DataFunctionWrapper<>(GroupTick.class, webEngine,
				(groupTick) -> {
					String transformString = "rotate(" + (((groupTick.angle() * 180) / Math.PI) - 90) + ")"
							+ "translate(" + outerRadius + ",0)";
					return transformString;
				});

		Selection ticks;
		try {

			ticks = svg.append("g") //
					.selectAll("g") //
					.data(chordGroups) //
					.enter() //
					.append("g") //
					.selectAll("g") //
					.data(groupTicksFunction) //
					.enter() //
					.append("g") //
					.attr("transform", transformGroupFunction);

			ticks.append("line") //
					.attr("x1", 1) //
					.attr("y1", 0) //
					.attr("x2", 5) //
					.attr("y2", 0) //
					.style("stroke", "#000");

			DataFunction<String> transformTextFunction = new DataFunctionWrapper<>(GroupTick.class, webEngine,
					(groupTick) -> {
						String transformString = groupTick.angle() > Math.PI ? "rotate(180)translate(-16)" : null;
						return transformString;
					});

			DataFunction<String> styleFunction = new DataFunctionWrapper<>(GroupTick.class, webEngine, (groupTick) -> {
				String style = groupTick.angle() > Math.PI ? "end" : null;
				return style;
			});

			DataFunction<String> textFunction = new DataFunctionWrapper<>(GroupTick.class, webEngine, (groupTick) -> {
				String text = groupTick.label();
				return text;
			});

			ticks.append("text") //
					.attr("x", 8) //
					.attr("dy", ".35em") //
					.attr("transform", transformTextFunction) //
					.style("text-anchor", styleFunction) //
					.text(textFunction);

			DataFunction<String> fillStyleFunction = new DataFunctionWrapper<>(ChordItem.class, webEngine,
					(chordItem) -> {
						int index = chordItem.target().index();
						String style = fillScale.apply(index).asString();
						return style;
					});

			org.treez.javafxd3.d3.svg.Chord chordPath = d3.svg().chord()//
					.radius(innerRadius);

			svg.append("g") //
					.attr("class", "chord") //
					.selectAll("path") //
					.data(chord.chords()) //
					.enter() //
					.append("path") //
					.attr("d", chordPath) //
					.style("fill", fillStyleFunction) //
					.style("opacity", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Double[][] matrix() {
		Double[][] matrix = new Double[][] { //
				{ 11975.0, 5871.0, 8916.0, 2868.0 }, //
				{ 1951.0, 10048.0, 2060.0, 6171.0 }, //
				{ 8010.0, 16145.0, 8090.0, 8045.0 }, //
				{ 1013.0, 990.0, 940.0, 6907.0 } //
		};
		return matrix;
	}

	private DataFunction<Void> fade(final Selection svg, double opacity) {

		return new IndexDataFunctionWrapper<>((index) -> {

			Selection selection = svg.selectAll(".chord path");
			Array<Element> array2d = selection.asElementArray();
			JSObject arrayObj = array2d.get(0, JSObject.class);
			Array<Element> array = new Array<>(webEngine, arrayObj);

			D3 d3 = new D3(webEngine);

			array.forEach((object) -> {

				JSObject jsObject = (JSObject) object;
				Element element = new Element(webEngine, jsObject);

				Array<JSObject> elementData = d3.select(element).data();

				JSObject jsData = elementData.get(0, JSObject.class);
				ChordItem chordItem = new ChordItem(webEngine, jsData);

				int sourceIndex = chordItem.source().index();
				int targetIndex = chordItem.target().index();
				boolean hideItem = (sourceIndex != index) && (targetIndex != index);
				if (hideItem) {
					d3.select(element) //
							.transition() //
							.style("opacity", opacity);
				}

			});
			return null;

		});

	}

	@Override
	public void stop() {
	}

	//#end region
	


}
