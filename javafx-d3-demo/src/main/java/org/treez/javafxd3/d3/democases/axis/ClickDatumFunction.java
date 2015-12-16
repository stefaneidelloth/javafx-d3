package org.treez.javafxd3.d3.democases.axis;

import java.util.List;

import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Transition;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.svg.Area;
import org.treez.javafxd3.d3.svg.Axis;
import org.treez.javafxd3.d3.svg.Line;
import org.treez.javafxd3.d3.time.TimeScale;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class ClickDatumFunction implements DatumFunction<Void> {

	//#region ATTRIBUTES

	private WebEngine webEngine;
	Selection svg;
	List<DsvData> valueList;
	private TimeScale xScale;	
	private Axis xAxis;	
	private Line line;
	private Area area;
	

	//#end region

	//#region CONSTRUCTORS

	public ClickDatumFunction(WebEngine webEngine, Selection svg, List<DsvData> valueList, TimeScale xScale, Axis xAxis, Line line, Area area) {
		this.webEngine = webEngine;
		this.svg = svg;
		this.valueList = valueList;
		this.xScale = xScale;	
		this.xAxis = xAxis;
		this.line = line;
		this.area = area;
		
	}

	//#end region

	//#region METHODS

	@Override
	public Void apply(final Object context, final Object d, final int index) {
		int n = valueList.size() - 1;
		int i = (int) Math.floor((Math.random() * n) / 2);
		int j = i + (int) Math.floor((Math.random() * n) / 2) + 1;
		JSObject firstObj = valueList.get(i).getDate().getJsObject();
		JSObject secondObj = valueList.get(j).getDate().getJsObject();
		xScale.domain(Array.fromJavaScriptObjects(webEngine, firstObj, secondObj));
		Transition transition = svg.transition().duration(750);
		transition.select("." + "x" + "." + "axis").call(xAxis);
		transition.select("." + "area").attr("d", area.apply(valueList));
		transition.select("." + "line").attr("d", line.generate(valueList));
		return null;
	};

	//#end region

}
