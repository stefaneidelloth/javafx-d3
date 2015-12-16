package org.treez.javafxd3.d3.democases.helloworld;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;

import javafx.scene.layout.VBox;

public class HelloWorldDemo extends AbstractDemoCase {

	//#region CONSTRUCTORS
	/**
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public HelloWorldDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
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
				return new HelloWorldDemo(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		Selection svg = getSvg();
		svg.attr("width", 700).attr("height",400);
		
		Selection text = svg.append("text") //
				.attr("x", "0").attr("y", "15").text("Hello World");		

	}

	@Override
	public void stop() {

	}

	//#end region

}
