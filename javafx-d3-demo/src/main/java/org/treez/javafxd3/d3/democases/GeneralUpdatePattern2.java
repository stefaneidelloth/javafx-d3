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
public class GeneralUpdatePattern2 extends AbstractDemoCase {

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
	public GeneralUpdatePattern2(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		//@Source("GeneralUpdatePattern2Styles.css")
		//gup2 updateD3Content enter
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
				return new GeneralUpdatePattern2(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		final char[] alphabet = new char[source.length()];
		source.getChars(0, source.length(), alphabet, 0);

		int width = 960, height = 500;

		svg = d3.select("root").append("svg").classed("gup2", true).attr("width", width)
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
		selection.attr("class", "updateD3Content");

		// ENTER
		// Create new elements as needed.
		selection.enter().append("text").attr("class", "enter").attr("dy", ".35em")
				.text(new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return "" + datum.asChar();
					}
				});

		// ENTER + UPDATE
		// Appending to the enter selection expands the update selection to
		// include
		// entering elements; so, operations on the update selection after
		// appending to
		// the enter selection will apply to both entering and updating nodes.
		selection.attr("x", new DatumFunction<Integer>() {
			@Override
			public Integer apply(final Object context, final Object datum, final int index) {
				return index * 32;
			}
		});

		// EXIT
		// Remove old elements as needed.
		selection.exit().remove();
	}

	@Override
	public void stop() {
		//timer.cancel();
		timer = null;
	}
	
	//#end region

}
