package org.treez.javafxd3.d3.democases.svg.brush;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.arrays.ForEachCallback;
import org.treez.javafxd3.d3.core.ObjectAccessor;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.democases.axis.DsvData;
import org.treez.javafxd3.d3.dsv.DsvCallback;
import org.treez.javafxd3.d3.dsv.DsvRow;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.scales.OrdinalScale;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Axis.Orientation;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.wrapper.Element;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.layout.VBox;
import netscape.javascript.JSObject;

/**
 * FIXME find another Slider component
 *
 * 
 *
 */
public class ScatterplotMatrixDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Brush brush;

	private LinearScale x;

	private LinearScale y;

	private Map<String, JavaScriptObject> domainByTrait;

	private Selection svg;

	private double padding;

	private int size;

	private OrdinalScale color;

	private Element brushCell;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public ScatterplotMatrixDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// this.css = Bundle.INSTANCE.css();
		// @Source("ScatterPlotMatrixDemo.css")
		// spm, axis, frame, hidden

		// this.addStyleName("spm");
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
				return new ScatterplotMatrixDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		size = 150;
		padding = 19.5;

		x = d3.scale().linear().range(padding / 2, size - padding / 2);

		y = d3.scale().linear().range(size - padding / 2, padding / 2);

		final Axis xAxis = d3.svg().axis().scale(x).orient(Orientation.BOTTOM).ticks(5);

		final Axis yAxis = d3.svg().axis().scale(y).orient(Orientation.LEFT).ticks(5);

		color = d3.scale().category10();

		d3.csv("demo-data/flowers.csv", new DsvCallback<DsvRow>() {

			@Override
			public void get(final Object error, final Object dsvRowArray) {
				
				if (error != null) {
					System.out.println("Cannot load flowers.csv: " + error);
					return;
				}
				
				domainByTrait = new HashMap<String, JavaScriptObject>();
				
				JSObject jsDsvDataArray = (JSObject) dsvRowArray;
				Array<DsvRow> values = new Array<DsvRow>(webEngine, jsDsvDataArray);
				List<? extends DsvRow> valueList = values.asList(DsvRow.class);
				
				String[] traits = d3.keys(valueList.get(0));
						
						
				//String filteredTraits = traits.filter(new ForEachCallback<Boolean>() {
				//	@Override
				//	public Boolean forEach(final Object thisArg, final Value element, final int index,
				//			final Object[] array) {
				//		return !"species".equals(element.asString());
				//	}
				//});
				
				final int n = traits.length;
				
				
				
				ForEachCallback<Void> forEachCallback = new ForEachCallback<Void>() {
					@Override
					public Void forEach(final Object thisArg, final Value trait, final int index,
							final Object[] array) {
						// for the current trait, get the extent=domain=(min and
						// max), and save it in the map
						// save the domain
						
						
						ObjectAccessor<DsvRow, Double> accessor = new ObjectAccessor<DsvRow, Double>() {
							@Override
							public Double apply(final DsvRow data, final int index) {
								return data.get(trait.asString()).asDouble();
							}
						};
						Value value = null; //Arrays.extent(data, accessor);
						
						
						domainByTrait.put(trait.asString(), value );
						return null;
					}
				};

				//traits.forEach(forEachCallback);

				xAxis.tickSize(size * n);
				yAxis.tickSize(-size * n);

				brush = d3.svg().brush().x(x).y(y).on(BrushEvent.BRUSH_START, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						brushstart(element, datum.<Point> as());
						return null;
					}
				}).on(BrushEvent.BRUSH, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;
						
						brushmove(datum.<Point> as());
						return null;
					}
				}).on(BrushEvent.BRUSH_END, new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						brushend();
						return null;
					}
				});

				svg = d3.select("root").append("svg").attr("width", size * n + padding)
						.attr("height", size * n + padding).append("g")
						.attr("transform", "translate(" + padding + "," + padding / 2 + ")");

				svg.selectAll(".x." + "axis").data(traits).enter().append("g").attr("class", "x " + "axis")
						.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						return "translate(" + (n - index - 1) * size + ",0)";
					}
				}).each(new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						x.domain(domainByTrait.get(datum.asString()));
						d3.select(element).call(xAxis);
						return null;
					}
				});

				svg.selectAll(".y." + "axis").data(traits).enter().append("g").attr("class", "y " + "axis")
						.attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						return "translate(0," + index * size + ")";
					}
				}).each(new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						y.domain(domainByTrait.get(datum.asString()));
						d3.select(element).call(yAxis);
						return null;
					}
				});

				Selection cell = svg.selectAll(".cell").data(cross(traits, traits)).enter().append("g")
						.attr("class", "cell").attr("transform", new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return "translate(" + (n - datum.<Point> as().i - 1) * size + "," + datum.<Point> as().j * size + ")";
					}
				}).each(new DatumFunction<Void>() {
					@Override
					public Void apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						plot(element, datum.<Point> as(), valueList);
						return null;
					}
				});

				// Titles for the diagonal.
				cell.filter(new DatumFunction<Element>() {
					@Override
					public Element apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return datum.<Point> as().i == datum.<Point> as().j ? element : null;
					}
				}).append("text").attr("x", padding).attr("y", padding).attr("dy", ".71em")
						.text(new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return datum.<Point> as().x;
					}
				});

				cell.call(brush);

				d3.select("root").style("height", size * n + padding + 20 + "px");
			}
		});

	}

	@Override
	public void stop() {

	}

	private void plot(final Element context, final Point p, final List<? extends DsvRow> data) {
		Selection cell = d3.select(context);

		x.domain(domainByTrait.get(p.x));
		y.domain(domainByTrait.get(p.y));

		cell.append("rect").attr("class", "frame").attr("x", padding / 2).attr("y", padding / 2)
				.attr("width", size - padding).attr("height", size - padding);

		cell.selectAll("circle").data(data).enter().append("circle").attr("cx", new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				Value v = datum.<DsvRow> as().get(p.x);
				String asString = x.apply(v.asDouble()).asString();
				return asString;
			}
		}).attr("cy", new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				double asDouble = datum.<DsvRow> as().get(p.y).asDouble();
				String asString = y.apply(asDouble).asString();
				return asString;
			}
		}).attr("r", 3).style("fill", new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return color.apply(datum.<DsvRow> as().get("species").asString()).asString();
			}
		});
	}

	// Clear the previously-active brush, if any.
	private void brushstart(final Element context, final Point p) {
		// Clear the previously-active brush, if any.
		if (brushCell != context) {
			d3.select(brushCell).call(brush.clear());
			x.domain(domainByTrait.get(p.x));
			y.domain(domainByTrait.get(p.y));
			brushCell = context;
		}
	}

	// Highlight the selected circles.
	private void brushmove(final Point p) {
		final Array<Double> e = brush.extent();
		svg.selectAll("circle").classed("hidden", new DatumFunction<Boolean>() {
			@Override
			public Boolean apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				// the plot coords
				double px = datum.<DsvRow> as().get(p.x).asDouble();
				double py = datum.<DsvRow> as().get(p.y).asDouble();
				// the extent of the brush
				double ex0 = e.get(0, 0, Double.class);
				double ey0 = e.get(0, 1, Double.class);
				double ex1 = e.get(1, 0, Double.class);
				double ey1 = e.get(1, 1, Double.class);
				// hide it (returns true) if the plot is outside the brush
				// extent
				boolean b = ex0 > px || px > ex1 || ey0 > py || py > ey1;
				return b;
			}
		});
	}

	// If the brush is empty, select all circles.
	private void brushend() {
		if (brush.empty()) {
			svg.selectAll(".hidden").classed("hidden", false);
		}
	}

	private static class Point {
		String x, y;
		double i, j;

		public Point(final String x, final String y, final int i, final int j) {
			super();
			this.x = x;
			this.y = y;
			this.i = i;
			this.j = j;
		}

	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private Object[] cross(final String[] a, final String[] b) {
		Point[] c = new Point[a.length];
		int n = a.length;
		int m = b.length, i, j;
		for (i = -1; ++i < n;) {
			for (j = -1; ++j < m;) {
				c[j] = new Point(a[i], b[j], i, j);
			}
		}
		return c;
	}

	//#end region

}
