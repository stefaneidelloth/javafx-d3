package org.treez.javafxd3.d3.democases.update;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.UpdateSelection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.DataFunction;
import org.treez.javafxd3.d3.functions.data.wrapper.DataFunctionWrapper;
import org.treez.javafxd3.d3.functions.data.wrapper.IndexDataFunctionWrapper;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 */
public class GeneralUpdatePattern1 extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Timer timer;
	private TimerTask timerTask;

	private Selection svg;

	//#end region

	//#region CONSTRUCTORS

	public GeneralUpdatePattern1(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
	}

	//#end region

	//#region METHODS

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

		svg = d3.select("svg") //				
				.attr("width", width) //
				.attr("height", height) //
				.append("g") //
				.attr("transform", "translate(32," + (height / 2) + ")");

		// The initial display.
		update(alphabet);

		timer = new Timer();

		timerTask = new TimerTask() {
			@Override
			public void run() {
				// Grab a random sample of letters from the alphabet, in
				// alphabetical order.
				d3.shuffle(alphabet);
				char[] range = new char[(int) Math.floor(Math.random() * 26)];
				System.arraycopy(alphabet, 0, range, 0, range.length);
				Arrays.sort(range);
				Platform.runLater(()->{update(range);});				
			}
		};

		timer.schedule(timerTask, 0, 1500);

	}

	public void update(final char[] data) {

		// DATA JOIN
		// Join new data with old elements, if any.
		UpdateSelection selection = svg.selectAll("text") //
				.data(data);

		// UPDATE
		// Update old elements as needed.
		selection.attr("class", "update");

		// ENTER
		// Create new elements as needed.

		DataFunction<Integer> xFunction = new IndexDataFunctionWrapper<>((index) -> {
			return index * 32;
		});

		selection.enter() //
				.append("text") //
				.attr("class", "enter") //
				.attr("x", xFunction) //
				.attr("dy", ".35em");

		// ENTER + UPDATE
		// Appending to the enter selection expands the update selection to
		// include
		// entering elements; so, operations on the update selection after
		// appending to
		// the enter selection will apply to both entering and updating nodes.

		DataFunction<String> textFunction = new DataFunctionWrapper<>(String.class, engine, (value) -> {			
			return value;
		});

		selection.text(textFunction);

		// EXIT
		// Remove old elements as needed.
		selection.exit().remove();
	}

	@Override
	public void stop() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	//#end region

}
