package org.treez.javafxd3.d3.democases.svg.symbol;

import java.util.Random;
import java.util.Stack;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transform;
import org.treez.javafxd3.d3.svg.Symbol;
import org.treez.javafxd3.d3.svg.SymbolType;

import javafx.scene.layout.VBox;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * 
 * 
 */
public class SymbolDemo extends AbstractDemoCase {

	//#region ATTRIBUTES

	private final Stack<Coords> points = new Stack<Coords>();
	private Selection svg;
	private Symbol symbols;
	protected int width = 450;
	protected int height = 320;

	//#end region

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public SymbolDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);

		//css = Bundle.INSTANCE.css();@Source("SymbolDemo.css")
		// symboldemo
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
				return new SymbolDemo(d3, demoPreferenceBox);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.gwtd3.demo.client.D3Demo#start()
	 */
	@Override
	public void start() {
		symbols = d3.svg().symbol();
		
		// create the things
		
		deleteOldPreferenceChildren();
		createButton("Add symbol", (event) -> addSymbol());

		svg = d3.select("svg").attr("width", width).attr("height", height).append("g");
	}

	protected void addSymbol() {
		Random random = new Random();

		symbols.type(SymbolType.values()[random.nextInt(SymbolType.values().length)]);
		symbols.size(random.nextInt(2500) + 25);

		Transform transform = new Transform(webEngine);
		Colors colors = new Colors(webEngine);

		int x = random.nextInt(width);
		int y = random.nextInt(height);
		String transformString = transform.parse("").translate(x, y).toString();

		Selection selection = svg.append("path").classed("symboldemo", true).attr("transform", transformString);

		String dString = symbols.generate(1.0);
		selection.attr("d", dString).style("fill",
				colors.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)).toHexaString());
	}

	@Override
	public void stop() {
		points.clear();
	}

	//#end region
}
