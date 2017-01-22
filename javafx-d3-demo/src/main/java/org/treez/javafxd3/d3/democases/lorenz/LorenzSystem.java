package org.treez.javafxd3.d3.democases.lorenz;

import java.util.Timer;
import java.util.TimerTask;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.demo.AbstractDemoCase;
import org.treez.javafxd3.d3.demo.DemoCase;
import org.treez.javafxd3.d3.demo.DemoFactory;
import org.treez.javafxd3.d3.functions.TimerFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.wrapper.canvas.CanvasElement;
import org.treez.javafxd3.d3.wrapper.canvas.Context2d;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.treez.javafxd3.d3.core.JsObject;

public class LorenzSystem extends AbstractDemoCase {

	//#region ATTRIBUTES

	private Context2d context;

	private boolean stopped = false;

	private final double width = 960;
	private final double height = 500;

	private Timer timer;
	private TimerTask timerTask;

	//private Context2d context;

	private LinearScale colorScale;
	private TimerFunction timerFunction;

	//#end region

	//#region CONSTRUCTORS

	public LorenzSystem(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);

		Selection canvas = d3.select("#root") //
				.insert("canvas", "svg") //				
				.attr("width", width) //
				.attr("height", height);

		colorScale = d3.scale() //
				.linear() //
				.domain(0, 20, 30, 50) //
				.range("yellow", "orange", "brown", "purple");

		JsObject jsCanvas = canvas.node()//
				.getJsObject();

		CanvasElement canvasElement = new CanvasElement(engine, jsCanvas);

		context = canvasElement.getContext2d();

		context.setLineWidth(.2);

		context.fillRect(0, 0, width, height);

		timerFunction = new LorenzTimerFunction(engine, this);

		timerTask = new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(() -> {
					context.setFillStyle("rgba(0,0,0,.03)");
				});
			}
		};

	}

	//#end region

	//#region METHODS

	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new LorenzSystem(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		stopped = false;
		d3.timer(timerFunction);

		timer = new Timer();
		timer.schedule(timerTask, 0, 100);
	}

	@Override
	public void stop() {
		stopped = true;
		timer.cancel();
	}

	//#end region

	//#region ACCESSORS

	public Context2d getContext() {
		return context;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public LinearScale getColorScale() {
		return colorScale;
	}

	public boolean getStopped() {
		return stopped;
	}

	//#end region

}
