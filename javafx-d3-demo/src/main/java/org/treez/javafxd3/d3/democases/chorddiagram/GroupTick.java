package org.treez.javafxd3.d3.democases.chorddiagram;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.core.JsObject;

public class GroupTick extends JavaScriptObject {

	//#region CONSCTRUCTORS

	public GroupTick(JsEngine engine, JsObject wrappedJsObject) {
		super(engine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public static GroupTick create(double angle, String label, JsEngine engine) {

		D3 d3 = new D3(engine);

		String command = "d3.new_group_tick = {" + //
				"angle: " + angle + ", " + //
				"label: '" + label + "'" + //
				"};";

		d3.eval(command);
		JsObject result = d3.evalForJsObject("d3.new_group_tick");

		d3.eval("d3.new_group_tick=undefined");
		return new GroupTick(engine, result);
	}

	//#end region

	//#region ACCESSORS

	public double angle() {
		Double angle = getMemberForDouble("angle");
		return angle;
	}

	public String label() {
		String label = getMemberForString("label");
		return label;
	}

	//#end region

}
