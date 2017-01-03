package org.treez.javafxd3.d3.democases;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.functions.KeyFunction;
import org.treez.javafxd3.d3.wrapper.Element;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import com.sun.glass.ui.Timer;

import javafx.scene.layout.VBox;
import netscape.javascript.JSObject;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 *
 * 
 *
 */
public class GeneralUpdatePattern3 extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Timer timer;
	private Selection svg;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public GeneralUpdatePattern3(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("GeneralUpdatePattern3Styles.css")
		// gup3, updateD3Content, enter, exit
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
				return new GeneralUpdatePattern3(d3, demoPreferenceBox);
			}
		};
	}

	
	@Override
	public void start() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		final char[] alphabet = new char[source.length()];
		source.getChars(0, source.length(), alphabet, 0);

		int width = 960, height = 500;

		svg = d3.select("root").append("svg").classed("gup3", true).attr("width", width)
				.attr("height", height).append("g").attr("transform", "translate(32," + (height / 2) + ")");

		// The initial display.
		update(alphabet);

		/*
		timer = new Timer() {
			@Override
			public void run() {
				// Grab a random sample of letters from the alphabet, in
				// alphabetical order.
				d3.shuffle(alphabet);
				char[] range = new char[(int) Math.floor(Math.random() * 26)];
				System.arraycopy(alphabet, 0, range, 0, range.length);
				Arrays.sort(range);
				update(range);
			}
		};
		timer.scheduleRepeating(1500);
		
		*/
	}

	public void update(final char[] data) {
		
		// DATA JOIN
		// Join new data with old elements, if any.
		UpdateSelection selection = svg.selectAll("text").data(data, new KeyFunction<Integer>() {

			@Override
			public Integer call(final Object context, final Object newDataArray, final Object datum, final int index) {
				
				JSObject jsObject = (JSObject) datum;
				Value value = new Value(webEngine, jsObject);
				return value.asInt();
			}
		});
		// UPDATE
		// Update old elements as needed.
		selection.attr("class", "updateD3Content").transition().duration(750).attr("x",
				new DatumFunction<Integer>() {
					@Override
					public Integer apply(final Object context, final Object d, final int index) {
						return index * 32;
					}
				});

		// ENTER
		// Create new elements as needed.
		selection.enter().append("text").attr("class", "enter").attr("dy", ".35em").attr("y", -60)
				.attr("x", new DatumFunction<Integer>() {
					@Override
					public Integer apply(final Object context, final Object d, final int index) {
						return index * 32;
					}
				}).style("fill-opacity", 0.01D).text(new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return Character.toString(datum.asChar());
					}
				})

		.transition().duration(750).attr("y", 0).style("fill-opacity", 1);

		// EXIT
		// Remove old elements as needed.
		selection.exit().attr("class", "exit").transition().duration(750).attr("y", 60)
				.style("fill-opacity", 0.01).remove();
	}

	@Override
	public void stop() {
		
		/*
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		
		*/
	}

	//#end region
}
