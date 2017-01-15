package org.treez.javafxd3.d3.democases.geom.mitchell;

import org.treez.javafxd3.d3.geom.Quadtree.RootNode;

import javafx.scene.web.WebEngine;

public class MitchellCircleGenerator implements CircleGenerator {
	
	//#region ATTRIBUTES

	private WebEngine webEngine;
	private double minDistance;
	double searchRadius;			
	double bestX;
	double bestY;
	double bestDistance = 0;
	
	private double width;
	private double height;
	private double padding;
	private double maxRadius;
	
	private RootNode<Circle> quadtree;

	//#end region
	
	//#region CONSTRUCTORS
	
	public MitchellCircleGenerator(WebEngine webEngine, RootNode<Circle> quadtree, double maxRadius, double width, double height, double padding){
		this.webEngine = webEngine;
		this.quadtree = quadtree;
		this.width = width;
		this.height = height;
		this.padding = padding;
		this.maxRadius = maxRadius;
		searchRadius = maxRadius*2;
	}
	
	//#end region

	//#region METHODS

	@Override
	public Circle generate(double k) {
		bestX = bestY = bestDistance = 0;

		for (int i = 0; (i < k) || (bestDistance < padding); ++i) {

			final double x = (Math.random() * width);
			final double y = (Math.random() * height);
			

			minDistance = maxRadius; // minimum distance for this candidate

			quadtree.visit(new MitchellVisitCallback(webEngine, this,x,y));

			if (minDistance > bestDistance) {
				bestX = x;
				bestY = y;
				bestDistance = minDistance;
			}
		}

		Circle best = new Circle(bestX, bestY, bestDistance - padding);
		quadtree.add(best);
		return best;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double d) {
		minDistance=d;
		
	}

	public Double getSearchRadius() {
		return searchRadius;
	}

	//#end region

}
