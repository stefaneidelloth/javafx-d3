package org.treez.javafxd3.d3.democases;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.TimerFunction;
import org.treez.javafxd3.d3.scales.LinearScale;

import com.sun.glass.ui.Timer;

import javafx.scene.layout.VBox;


@SuppressWarnings("javadoc")
public class LorenzSystem extends AbstractDemoCase {

	//#region ATTRIBUTES

	private boolean stopped = false;
	private final int n = 30;
	private final double width = 960, height = 500;
	private final double a=0.003;
	private final double b=28;
	private final double c=10;
	private final double d=8/3;
	private double x = .5;
	private double y = .5;
	private double z = 10;
	private Timer timer;
	
	//private Context2d context;
	
	private LinearScale color;
	private TimerFunction timerFunction;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public LorenzSystem(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// 
	

		Selection canvas = d3.select("root").append("canvas")
				.attr("width", width).attr("height", height);
		
		color = d3.scale().linear().domain(0, 20, 30, 50)
				.range("yellow", "orange", "brown", "purple");
		
		
		/*
		context = canvas.node().<CanvasElement> cast().getContext2d();
		context.setLineWidth(.2);
		context.setFillStyle("rgba(0,0,0,.03)");
		*/

		/*
		
		timerFunction = new TimerFunction() {
			@Override
			public boolean execute() {
				context.save();
				context.setGlobalCompositeOperation("lighter");
				context.translate(width / 2, height / 2);
				context.scale(12, 14);
				context.rotate(30);
				for (int i = 0; i < n; ++i) {
					context.setStrokeStyle(Colors
							.rgb(color.apply(z).asString()).toHexaString());
					context.beginPath();
					context.moveTo(x, y);
					x += δτ * σ * (y - x);
					y += δτ * ((x * (�? - z)) - y);
					z += δτ * ((x * y) - (β * z));
					context.lineTo(x, y);
					context.stroke();
				}
				context.restore();
				return stopped;
			}
		};
		timer = new Timer() {
			@Override
			public void run() {
				context.fillRect(0, 0, width, height);
			}
		};
		
		*/

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
				return new LorenzSystem(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {
		stopped = false;
		d3.timer(timerFunction);
		//timer.scheduleRepeating(100);
	}

	@Override
	public void stop() {
		stopped = true;
		//timer.cancel();
	}
	
	
	//#end region

}
