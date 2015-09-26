package com.github.javafxd3.demo.client.democases.geom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.coords.Coords;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.UpdateSelection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.geom.Hull;
import com.github.javafxd3.api.wrapper.Element;
import com.github.javafxd3.demo.client.AbstractDemoCase;
import com.github.javafxd3.demo.client.DemoCase;
import com.github.javafxd3.demo.client.DemoFactory;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * 
 * 
 */
public class HullDemo extends AbstractDemoCase {

	

	// #region ATTRIBUTES

	private Selection svg;
	private Selection hull;
	private Selection circle;
	private List<MyCoords> vertices;
	private Hull hullModel;
	
	private static int width = 960;
	private static int height = 500;

	// #end region
	
	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public HullDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// @Source("HullDemo.css")
				// hulldemo
	}
	
	
	//#end region
	
	//#region METHODS

	/**
	 * Factory provider
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {

			@Override
			public DemoCase newInstance() {
				return new HullDemo(d3, demoPreferenceBox);
			}			
		};	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.gwtd3.demo.client.D3Demo#start()
	 */
	@Override
	public void start() {
		
		
		List<MyCoords> coordsList = new ArrayList<>();		
		for (int index = 0; index<100;index++){
			MyCoords coords = new MyCoords(webEngine, randomX(), randomY());			
			coordsList.add(coords);			
		}

		vertices = coordsList; //coordsList.toArray(new MyCoords[coordsList.size()]);
		

		svg = d3.select("root").append("svg").attr("width", width).attr("height", height)
				.on("MOUSEMOVE", new DatumFunction<Void>() {
					@Override
					public Void apply(Element context, Value d, int index) {
						MyCoords coords = new MyCoords(webEngine, d3.mouseX(context), d3.mouseY(context));
						vertices.set(0,  coords);
						redraw();
						return null;
					}
				}).on("CLICK", new DatumFunction<Void>() {
					@Override
					public Void apply(Element context, Value d, int index) {
						MyCoords coords = new MyCoords(webEngine, d3.mouseX(context), d3.mouseY(context));
						vertices.add(coords);
						redraw();
						return null;
					}
				});

		svg.append("rect").attr("width", width).attr("height", height).attr("class", "hulldemo");

		hull = svg.append("path").attr("class", "hulldemo");

		circle = svg.selectAll("circle");

		hullModel = d3.geom().hull().x(MyCoords.xAccessor()).y(MyCoords.yAccessor());

		redraw();
	}
	
	

	private void redraw() {
		try {
			hull.datum(hullModel.apply(vertices)).attr("d", new DatumFunction<String>() {
				@Override
				public String apply(Element context, Value d, int index) {
					List<MyCoords> coordsList = d.<List<MyCoords>> as();
					List<String> coordsStringList = new ArrayList<>();
					for(MyCoords coords : coordsList){
						String coordsString = coords.toString();
						coordsStringList.add(coordsString);
					}
					String coordsString = String.join("L", coordsStringList);
					String result = "M" + coordsString + "Z";
					return result;
				}
			});
			UpdateSelection circles = circle.data(vertices);
			circles.enter().append("circle").attr("r", 3);
			circle = circles.attr("transform", new DatumFunction<String>() {
				@Override
				public String apply(Element context, Value d, int index) {
					return "translate(" + d.<Coords> as() + ")";
				}
			}).attr("class", "hulldemo");
			circle = circles;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
	}
	
	private double randomX(){
		double xmin = width/2;
		double xmax = 60d;
		double range = xmax-xmin;
		Random random = new Random();
		double randomX = xmin + random.nextDouble() * range; 
		return randomX;
		
	}
	
	private double randomY(){
		double ymin = height/2;
		double ymax = 60d;
		double range = ymax-ymin;
		Random random = new Random();
		double randomY = ymin + random.nextDouble() * range; 
		return randomY;
		
	}
	
	//#end region
	
	//#regon CLASSES

	private static class MyCoords extends Coords {
		

		public MyCoords(WebEngine webEngine, double x, double y) {
			super(webEngine, x,y);			
		}

		@Override
		public String toString() {
			return x() + "," + y();
		}

		public static DatumFunction<Double> xAccessor() {
			return new DatumFunction<Double>() {

				@Override
				public Double apply(Element context, Value d, int index) {					
					return null;
				}
		};
		}

		public static DatumFunction<Double> yAccessor() {
			return new DatumFunction<Double>() {

				@Override
				public Double apply(Element context, Value d, int index) {
					// TODO Auto-generated method stub
					return null;
				}
		};
		}

	}
		
		//#end region

}
