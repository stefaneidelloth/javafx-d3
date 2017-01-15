package org.treez.javafxd3.d3.democases.chorddiagram;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.wrapper.JavaScriptObject;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class GroupTick extends JavaScriptObject {

	//#region CONSCTRUCTORS

	public GroupTick(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public static GroupTick create(double angle, String label, WebEngine webEngine) {

		D3 d3 = new D3(webEngine);

		String command = "d3.new_group_tick = {" + //
				"angle: " + angle + ", " + //
				"label: '" + label + "'" + //
				"};";

		d3.eval(command);
		JSObject result = d3.evalForJsObject("d3.new_group_tick");

		d3.eval("d3.new_group_tick=undefined");
		return new GroupTick(webEngine, result);
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
