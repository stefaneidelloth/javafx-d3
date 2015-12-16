package org.treez.javafxd3.d3.democases.svg.brush;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.arrays.Array;
import org.treez.javafxd3.d3.coords.Coords;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.event.D3Event;
import org.treez.javafxd3.d3.functions.DatumFunction;
import org.treez.javafxd3.d3.geom.Quadtree.Callback;
import org.treez.javafxd3.d3.geom.Quadtree.Node;
import org.treez.javafxd3.d3.geom.Quadtree.RootNode;
import org.treez.javafxd3.d3.scales.IdentityScale;
import org.treez.javafxd3.d3.svg.Brush;
import org.treez.javafxd3.d3.svg.Brush.BrushEvent;
import org.treez.javafxd3.d3.wrapper.Element;

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 *
 */
public class BrushTransitionsDemo extends AbstractDemoCase {

//#region ATTRIBUTES
   
    private Brush brush;
    private Selection svg;
    private Selection point;
    
 //#end region

 	//#region CONSTRUCTORS

 	/**
 	 * @param d3
 	 * @param demoPreferenceBox
 	 */
 	public BrushTransitionsDemo(D3 d3, VBox demoPreferenceBox) {
 		super(d3, demoPreferenceBox);
 		// this.css = Bundle.INSTANCE.css(); @Source("BrushTransitionsDemo.css")
 		// bt, point, selected, brush
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
                return new BrushTransitionsDemo(d3, demoPreferenceBox);
            }
        };
    }
   

    @Override
    public void start() {
        final int width = 960;
        final int height = 500;
       
        final Double[][] defaultExtent = new Double[][]{ {100.0, 100.0}, { 300.0, 300.0} };

        /*
         * Not yet implemented
         * 
        Point[] data = d3.range(5000.0).map(new ForEachCallback<Point>() {
            @Override
            public Point forEach(final Object thisArg, final Value element, final int index, final Object[] array) {
                return new Coords(webEngine, Math.random() * width, Math.random() * width).cast();
            }
        });
        
        */
        Point[] data = new Point[]{new Point(webEngine, 1.0, 2.0), new Point(webEngine, 10.0, 20.0)};

        final RootNode<Point> quadtree = d3.geom().quadtree()
                .extent(-1, -1, width + 1, height + 1)
                .x(Coords.getXAccessor(webEngine))
                .y(Coords.getYAccessor(webEngine))
                .apply(data);

        IdentityScale x = d3.scale().identity().domain(0, width);
        IdentityScale y = d3.scale().identity().domain(0, height);

        brush = d3.svg().brush()
                .x(x)
                .y(y)
                .extent(defaultExtent)
                .on(BrushEvent.BRUSH, new DatumFunction<Void>() {
                    @Override
                    public Void apply(final Object context, final Object d, final int index) {
                        Array<Double> extent = brush.extent();
                        point.each(new DatumFunction<Void>() {
                            @Override
                            public Void apply(final Object context, final Object d, final int index) {
                            	
                            	Value datum = (Value) d;
                                datum.<Point> as().setSelected(false);
                                return null;
                            }
                        });
                        search(quadtree,
                                extent.get(0,0, Double.class),
                                extent.get(0,1, Double.class),
                                extent.get(1,0, Double.class),
                                extent.get(1,1, Double.class));
                        point.classed("selected", new DatumFunction<Boolean>() {
                            @Override
                            public Boolean apply(final Object context, final Object d, final int index) {
                            	Value datum = (Value) d;
                                return datum.<Point> as().isSelected();
                            }
                        });
                        return null;
                    }
                })
                .on(BrushEvent.BRUSH_END, new DatumFunction<Void>() {
                    @Override
                    public Void apply(final Object context, final Object d, final int index) {
                       // if (d3.<D3Event> event().sourceEvent() == null) {
                            return null; // only transition after input
                       // }
                        
                       // Element element = (Element) context;
                        /*
                        d3.select(element).transition()
                        .duration(brush.empty() ? 0 : 750)
                        .call(brush.extent(defaultExtent))
                        .call(brush.event());
                        return null;
                        */
                    }
                });

        svg = d3.select("root").append("svg")
                .attr("width", width)
                .attr("height", height);

        point = svg.selectAll("." + "point")
                .data(data)
                .enter().append("circle")
                .attr("class", "point")
                .attr("cx", new DatumFunction<Double>() {
                    @Override
                    public Double apply(final Object context, final Object d, final int index) {
                    	
                    	Value datum = (Value) d;
                    	
                        return datum.<Coords> as().x();
                    }
                })
                .attr("cy", new DatumFunction<Double>() {
                    @Override
                    public Double apply(final Object context, final Object d, final int index) {
                    	
                    	Value datum = (Value) d;
                    	
                        return datum.<Coords> as().y();
                    }
                })
                .attr("r", 4);

        svg.append("g")
        .attr("class", "brush")
        .call(brush)
        .call(brush.event());

    }

    // Find the nodes within the specified rectangle.
    private void search(final RootNode<Point> quadtreeRoot, final double x0, final double y0, final double x3,
            final double y3) {
        quadtreeRoot.visit(new Callback<Point>() {
            @Override
            public boolean visit(final Object nodeObj, final double x1, final double y1, final double x2,
                    final double y2) {
            	
            	JSObject jsNodeObj = (JSObject) nodeObj;
            	RootNode<Point> node = new RootNode<Point>(webEngine, jsNodeObj);
                Point p = node.point(Point.class);
                
                if (p != null) {
                    p.setSelected((p.x() >= x0) && (p.x() < x3) && (p.y() >= y0) && (p.y() < y3));
                }
                return x1 >= x3 || y1 >= y3 || x2 < x0 || y2 < y0;
            }
        });
    }

    @Override
    public void stop() {

    }

   
    
    //#end region
    
    //#region CLASSES
    
    /**
     * Represents a point that can be selected
     */
    public static class Point extends Coords {
    	
    	//#region CONSTRUCTORS
    	
        /**
         * Constructor
         * @param webEngine
         * @param wrappedJsObject
         */
        protected Point(WebEngine webEngine, JSObject wrappedJsObject) {
        	super(webEngine, wrappedJsObject);
        }
        
        /**
         * Constructor
         * @param webEngine
         * @param wrappedJsObject
         */
        protected Point(WebEngine webEngine, double x, double y) {
        	super(webEngine, x, y);
        }
        
        //#end region
        
        //#region METHODS

        /**
         * @param isSelected
         */
        public void setSelected(boolean isSelected){
        	String command = "this.selected = " + isSelected + ";";
        	eval(command);
        }

        /**
         * @return
         */
        public boolean isSelected(){
        	Boolean result = getMemberForBoolean("selected");
        	return result;        			
        }
        
        //#end region

    }
    
    //#end region

}
