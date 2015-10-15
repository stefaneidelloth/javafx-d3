package com.github.javafxd3.demo.client.democases.svg.symbol;

import java.util.Random;
import java.util.Stack;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.color.Colors;
import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Transform;
import com.github.javafxd3.api.svg.Symbol;
import com.github.javafxd3.api.svg.Symbol.Type;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

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
		createButton("Add symbol", (event)->addSymbol());
			
		svg = d3.select("svg").attr("width", width)
				.attr("height", height).append("g");
	}

	

	protected void addSymbol() {
		Random random = new Random();
		
		symbols.type(Type.values()[random.nextInt(Type.values().length)]);
		symbols.size(random.nextInt(2500) + 25);
		
		Transform transform = new Transform(webEngine);
		Colors colors = new Colors(webEngine);
		
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		String transformString = transform.parse("").translate(x, y).toString();

		Selection selection = svg.append("path")
				.classed("symboldemo", true)
				.attr("transform", transformString);
		
		String dString = symbols.generate(1.0);
		selection.attr("d", dString)
				.style("fill",
						colors.rgb(random.nextInt(255), 
						random.nextInt(255),
						random.nextInt(255)
					   ).toHexaString());
	}

	@Override
	public void stop() {
		points.clear();
	}

	
	
	//#end region
}
