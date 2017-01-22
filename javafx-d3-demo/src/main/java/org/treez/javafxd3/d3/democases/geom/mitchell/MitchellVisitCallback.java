package org.treez.javafxd3.d3.democases.geom.mitchell;

import org.treez.javafxd3.d3.core.ConversionUtil;
import org.treez.javafxd3.d3.core.JsEngine;
import org.treez.javafxd3.d3.geom.Quadtree.Callback;
import org.treez.javafxd3.d3.geom.Quadtree.Node;

public class MitchellVisitCallback implements Callback<Circle> {

	//#region ATTRIBUTES

	private JsEngine engine;
	private MitchellCircleGenerator parent;
	private double x;
	private double y;

	private double rx1;
	private double rx2;
	private double ry1;
	private double ry2;

	//#end region

	//#region CONSTRUCTORS

	public MitchellVisitCallback(JsEngine engine, MitchellCircleGenerator parent, Double x, Double y) {
		this.engine = engine;
		this.parent = parent;
		this.x = x;
		this.y=y;
		
		Double searchRadius = parent.getSearchRadius();
		rx1 = x - searchRadius;
		rx2 = x + searchRadius;
		ry1 = y - searchRadius;
		ry2 = y + searchRadius;

	}

	//#end region

	//#region METHODS

	@Override
	public boolean visit(Object quadObj, Double x1, Double y1, Double x2, Double y2) {
		
		if (quadObj==null){
			return false;
		}		
		
		@SuppressWarnings("unchecked")
		Node<Circle> quad = (Node<Circle>) ConversionUtil.convertObjectTo(quadObj, Node.class, engine);
		
		Circle p = quad.point(Circle.class);
		if (p != null) {
			double dx = x - p.x;
			double dy = y - p.y;
			double d2 = (dx * dx) + (dy * dy);
			double r2 = p.r * p.r;
			if (d2 < r2) {
				parent.setMinDistance(0);
				return true;
			}
			// within a circle
			double d = (Math.sqrt(d2) - p.r);
			if (d < parent.getMinDistance()) {
				parent.setMinDistance(d);
			}
		}
		// outside
		// search
		// radius
		boolean result = (parent.getMinDistance() == 0) || (x1 > rx2) || (x2 < rx1) || (y1 > ry2) || (y2 < ry1); // or
		return result;
	}

	//#end region

}
