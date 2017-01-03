package org.treez.javafxd3.d3.democases.chorddiagram;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

@SuppressWarnings("javadoc")
public class ChordDiagram extends AbstractDemoCase {

	//#region ATTRIBUTES

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ChordDiagram(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("ChordDiagramStyles.css")
		// chord
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
		
		/*

		Chord chord = d3.layout().chord().padding(.05).sortSubgroups(Arrays.descending()).matrix(matrix);

		int width = 960;
		int height = 500;
		double innerRadius = Math.min(width, height) * .41;
		final double outerRadius = innerRadius * 1.1;

		final OrdinalScale fill = d3.scale().ordinal().domain(Arrays.range(4))
				.range(new Object[] { "#000000", "#FFDD89", "#957244", "#F26223" });

		final Selection svg = d3.select("root").append("svg").attr("width", width).attr("height", height).append("g")
				.attr("transform", "translate(" + (width / 2) + "," + (height / 2) + ")");

		DatumFunction<String> indexFunction = new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Value d, final int index) {
				try {
					int i = d.<Group> as().index();
					Value apply = fill.apply(i);
					String string = apply.asString();
					return string;
				} catch (Exception e) {
					e.printStackTrace();
					return "blah2";
				}
			}
		};

		svg.append("g").selectAll("path").data(chord.groups()).enter().append("path").style("fill", indexFunction)
				.style("stroke", indexFunction)
				.attr("d", d3.svg().arc().innerRadius(innerRadius).outerRadius(outerRadius))
				.on("mouseover", fade(svg, .1)).on("mouseout", fade(svg, 1));

		// Returns an array of tick angles and labels, given a group.
		DatumFunction<GroupTick[]> groupTicks = new DatumFunction<GroupTick[]>() {
			@Override
			public GroupTick[] apply(final Element context, final Value d, final int index) {
				final Group g = d.<Group> as();
				final double k = (g.endAngle() - g.startAngle()) / g.value();
				return Arrays.range(0, g.value(), 1000).<Double[]> cast().map(new ForEachCallback<GroupTick>() {
					@Override
					public GroupTick forEach(final Object thisArg, final Value v, final int index,
							final Object[] array) {
						double angle = (v.asDouble() * k) + g.startAngle();
						String label = (index % 5) != 0 ? null : (v.asDouble() / 1000) + "k";
						return GroupTick.create(angle, label);
					}
				});
			}
		};

		Selection ticks;
		try {
			ticks = svg.append("g").selectAll("g").data(chord.groups()).enter().append("g").selectAll("g")
					.data(groupTicks).enter().append("g").attr("transform", new DatumFunction<String>() {
						@Override
						public String apply(final Element context, final Value d, final int index) {
							GroupTick groupTick = d.<GroupTick> as();
							return "rotate(" + (((groupTick.angle() * 180) / Math.PI) - 90) + ")" + "translate("
									+ outerRadius + ",0)";
						}
					});
			ticks.append("line").attr("x1", 1).attr("y1", 0).attr("x2", 5).attr("y2", 0).style("stroke", "#000");

			ticks.append("text").attr("x", 8).attr("dy", ".35em").attr("transform", new DatumFunction<String>() {
				@Override
				public String apply(final Element context, final Value d, final int index) {
					return d.<GroupTick> as().angle() > Math.PI ? "rotate(180)translate(-16)" : null;
				}
			}).style("text-anchor", new DatumFunction<String>() {
				@Override
				public String apply(final Element context, final Value d, final int index) {
					return d.<GroupTick> as().angle() > Math.PI ? "end" : null;
				}
			}).text(new DatumFunction<String>() {
				@Override
				public String apply(final Element context, final Value d, final int index) {
					return d.<GroupTick> as().label();
				}
			});

			svg.append("g").attr("class", "chord").selectAll("path").data(chord.chords()).enter().append("path")
					.attr("d", d3.svg().chord().radius(innerRadius)).style("fill", new DatumFunction<String>() {
						@Override
						public String apply(final Element context, final Value d, final int index) {
							return fill.apply(d.<ChordItem> as().target().index()).asString();
						}
					}).style("opacity", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/

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

	private static String call(JavaScriptObject functionObj) {
		String command = "this()";
		String result = (String) functionObj.eval(command);
		return result;		
	}

	private DatumFunction<Void> fade(final Selection svg, final double opacity) {
		return new DatumFunction<Void>() {
			@Override
			public Void apply(final Object context, final Object d, final int i) {

				Selection selection = svg.selectAll("." + "chord" + " path");

				//ChordItem[] chordItems = selection.<ChordItem[]> cast();

				//ChordItem[] filteredChordItems = chordItems;

				/*
				 * chordItems.filter(new ForEachCallback<Boolean>() {
				 * 
				 * @Override public Boolean forEach(final Object thisArg, final
				 * Value v, final int index, final Object[] array) { return
				 * (v.as(ChordItem.class).source().index() != i) &&
				 * (v.as(ChordItem.class).target().index() != i); } })
				 */

				// filteredChordItems.<Selection>
				// cast().transition().style("opacity", opacity);

				return null;
			}
		};
	}

	@Override
	public void stop() {
	}

	//#end region

	//#region CLASSES

	private static class GroupTick extends JavaScriptObject {
		
		public GroupTick(WebEngine webEngine) {
			super(webEngine);
		}
		
		/*

		

		public static GroupTick create(double angle, String label) {
																				return {
																				angle : angle,
																				label : label
																				};
																				}

		public double angle() {
			return this.angle;
		}

		public String label() {
			return this.label;
		}
		
		*/

	}

	//#end region

}
