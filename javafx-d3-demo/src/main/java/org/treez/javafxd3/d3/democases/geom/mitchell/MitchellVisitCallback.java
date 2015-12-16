package org.treez.javafxd3.d3.democases.geom.mitchell;

import org.treez.javafxd3.d3.geom.Quadtree.Callback;
import org.treez.javafxd3.d3.geom.Quadtree.Node;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class MitchellVisitCallback implements Callback<Circle> {

	//#region ATTRIBUTES

	private WebEngine webEngine;
	private MitchellCircleGenerator parent;
	private double x;
	private double y;

	private double rx1;
	private double rx2;
	private double ry1;
	private double ry2;

	//#end region

	//#region CONSTRUCTORS

	public MitchellVisitCallback(WebEngine webEngine, MitchellCircleGenerator parent, Double x, Double y) {
		this.webEngine = webEngine;
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
	public boolean visit(final Object quadObj, final double x1, final double y1, final double x2, final double y2) {
		
		if (quadObj==null){
			return false;
		}
		
		JSObject jsQuad = (JSObject) quadObj;
		//Inspector.inspect(jsQuad);
		
		Node<Circle> quad = new Node<Circle>(webEngine, jsQuad);
		
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
