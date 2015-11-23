package com.github.javafxd3.demo.client.democases.geom.mitchell;

import com.github.javafxd3.api.geom.Quadtree.Callback;
import com.github.javafxd3.api.geom.Quadtree.Node;

public class MitchellVisitCallback implements Callback<Circle> {

	//#region ATTRIBUTES

	MitchellCircleGenerator parent;
	double x;
	double y;

	double rx1;
	double rx2;
	double ry1;
	double ry2;

	//#end region

	//#region CONSTRUCTORS

	public MitchellVisitCallback(MitchellCircleGenerator parent, Double x, Double y) {
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
	public boolean visit(final Node<Circle> quad, final double x1, final double y1, final double x2, final double y2) {
		
		if (quad==null){
			return false;
		}
		Circle p = quad.point();
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
		return (parent.getMinDistance() == 0) || (x1 > rx2) || (x2 < rx1) || (y1 > ry2) || (y2 < ry1); // or
	}

	//#end region

}
