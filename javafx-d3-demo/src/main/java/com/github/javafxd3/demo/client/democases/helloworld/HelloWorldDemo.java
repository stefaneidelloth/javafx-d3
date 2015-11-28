package com.github.javafxd3.demo.client.democases.helloworld;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.core.Selection;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

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
