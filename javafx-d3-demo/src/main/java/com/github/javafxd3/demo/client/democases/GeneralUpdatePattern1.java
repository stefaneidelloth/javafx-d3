package com.github.javafxd3.demo.client.democases;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.UpdateSelection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;
import com.sun.glass.ui.Timer;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 *
 * 
 *
 */
public class GeneralUpdatePattern1 extends AbstractDemoCase {

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
	public GeneralUpdatePattern1(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		//@Source("GeneralUpdatePattern1Styles.css")
		//gup1, updateD3Content, enter
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
				return new GeneralUpdatePattern1(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		final char[] alphabet = new char[source.length()];
		source.getChars(0, source.length(), alphabet, 0);

		int width = 960, height = 500;

		svg = d3.select("root").append("svg").classed("gup1", true).attr("width", width)
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
		UpdateSelection selection = svg.selectAll("text").data(data);

		// UPDATE
		// Update old elements as needed.
		selection.attr("class", "updateD3Content");

		// ENTER
		// Create new elements as needed.
		selection.enter().append("text").attr("class", "enter")
				.attr("x", new DatumFunction<Integer>() {
					@Override
					public Integer apply(final Object context, final Object datum, final int index) {
						return index * 32;
					}
				}).attr("dy", ".35em");

		// ENTER + UPDATE
		// Appending to the enter selection expands the update selection to
		// include
		// entering elements; so, operations on the update selection after
		// appending to
		// the enter selection will apply to both entering and updating nodes.
		selection.text(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object d, final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return Character.toString(datum.asChar());
			}
		});

		// EXIT
		// Remove old elements as needed.
		selection.exit().remove();
	}

	@Override
	public void stop() {
		if (timer != null) {
			//timer.cancel();
			timer = null;
		}
	}
	
	//#end region

}
