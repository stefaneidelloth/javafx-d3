package org.treez.javafxd3.d3.democases.lorenz;

import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.functions.TimerFunction;
import org.treez.javafxd3.d3.scales.LinearScale;
import org.treez.javafxd3.d3.wrapper.canvas.Context2d;

import javafx.scene.web.WebEngine;

public class LorenzTimerFunction implements TimerFunction {
	
	//#region ATTRIBUTES

	private WebEngine webEngine;

	private LorenzSystem lorenzSystem;

	private Context2d context;

	private double x = .5;
	private double y = .5;
	private double z = 10;

	private final int n = 30;

	private double width;
	private double height;
	private LinearScale colorScale;
	
	//#end region
	
	//#region CONSTRUCTORS

	public LorenzTimerFunction(WebEngine webEngine, LorenzSystem lorenzSystem) {
		this.webEngine = webEngine;
		this.lorenzSystem = lorenzSystem;

		this.context = lorenzSystem.getContext();
		this.width = lorenzSystem.getWidth();
		this.height = lorenzSystem.getHeight();
		this.colorScale = lorenzSystem.getColorScale();
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public boolean execute() {
		context.save();
		context.setGlobalCompositeOperation("lighten");
		context.translate(width / 2, height / 2);
		context.scale(12, 14);
		context.rotate(30);

		double dTau = 0.003;
		double rho = 28;
		double sigma = 10;
		double beta = 8 / 3;

		Colors colors = new Colors(webEngine);

		for (int i = 0; i < n; ++i) {

			String colorRgb = colorScale.apply(z).asString();
			org.treez.javafxd3.d3.color.RGBColor rgbColor = colors.rgb(colorRgb);

			String colorString = rgbColor.toHexaString();

			context.setStrokeStyle(colorString);
			context.beginPath();
			context.moveTo(x, y);

			x += dTau * sigma * (y - x);
			y += dTau * ((x * (rho - z)) - y);
			z += dTau * ((x * y) - (beta * z));
			context.lineTo(x, y);
			context.stroke();
		}
		context.restore();
		return lorenzSystem.getStopped();
	}
	
	//#end region

}
