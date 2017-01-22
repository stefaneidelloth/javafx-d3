package org.treez.javafxd3.d3.democases.svg.brush.transitions;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.democases.svg.brush.transitions.BrushTransitionsDemo.Point;
import org.treez.javafxd3.d3.geom.Quadtree.Callback;
import org.treez.javafxd3.d3.geom.Quadtree.RootNode;

public class BrushTransitionsDemoCallback implements Callback<Point> {

	//#region ATTRIBUTES

	private JsEngine engine;

	private double x0;
	private double y0;
	private double x3;
	private double y3;

	//#end region

	//#region CONSTURCTORS

	public BrushTransitionsDemoCallback(JsEngine engine, final double x0, final double y0, final double x3,
			final double y3) {
		this.engine = engine;
		this.x0 = x0;
		this.y0 = y0;
		this.x3 = x3;
		this.y3 = y3;
	}

	//#end region

	//#region METHODS

	@Override
	public boolean visit(Object nodeObj, Double x1, Double y1, Double x2, Double y2) {

		
		@SuppressWarnings("unchecked")
		RootNode<Point> node = ConversionUtil.convertObjectTo(nodeObj,  RootNode.class, engine);
		Point p = node.point(Point.class);

		if (p != null) {
			p.setSelected((p.x() >= x0) && (p.x() < x3) && (p.y() >= y0) && (p.y() < y3));
		}
		return x1 >= x3 || y1 >= y3 || x2 < x0 || y2 < y0;
	}

	//#end region

}
