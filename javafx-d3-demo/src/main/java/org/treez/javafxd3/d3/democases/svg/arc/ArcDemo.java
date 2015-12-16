package org.treez.javafxd3.d3.democases.svg.arc;

import org.treez.javafxd3.d3.AbstractDemoCase;
import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.DemoCase;
import org.treez.javafxd3.d3.DemoFactory;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.svg.Arc;

import javafx.scene.layout.VBox;


/**
 * FIXME find another Slider component
 * 
 * 
 * 
 */
public class ArcDemo extends AbstractDemoCase {

	
	//#region CONSTRUCTORS
    /**
     * Constructor
     * @param d3
     * @param demoPreferenceBox
     */
    public ArcDemo(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);
		// TODO Auto-generated constructor stub
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
				return new ArcDemo(d3, demoPreferenceBox);
			}
		};
	}
	
	@Override
    public void start() {
		
				
		//Arc arc = d3.svg().arc().innerRadius(40).outerRadius(100).startAngle(0).endAngle(Math.PI);
		
		//Selection svg = root.append("g").attr("transform", "translate(200,200)");
	    //svg.append("path");
			   

    }

    @Override
    public void stop() {
	// TODO Auto-generated method stub

    }
    //
    // private static ArcDemoUiBinder uiBinder =
    // GWT.create(ArcDemoUiBinder.class);
    //
    // interface ArcDemoUiBinder extends UiBinder<Widget, ArcDemo> {
    // }
    //
    // @UiField
    // SVGDocumentContainer svgWidget;
    //
    // Selection svg;
    //
    // @UiField
    // Slider innerRadius;
    // @UiField
    // Slider outerRadius;
    // @UiField
    // Slider startAngle;
    // @UiField
    // Slider endAngle;
    //
    // private final Arc arc;
    //
    // public ArcDemo() {
    // initWidget(ArcDemo.uiBinder.createAndBindUi(this));
    //
    // // svgWidget.translate(200, 200);
    // arc = d3.svg().arc().innerRadius(40)
    // .outerRadius(100)
    // .startAngle(0)
    // .endAngle(Math.PI);
    // svg = svgWidget.select().append("g").attr("transform",
    // "translate(200,200)");
    // svg.append("path");
    // update();
    // }
    //
    // @UiHandler("innerRadius")
    // public void onInnerRadius(final ValueChangeEvent<Double> e) {
    // update();
    // }
    //
    // @UiHandler("outerRadius")
    // public void onOuterRadius(final ValueChangeEvent<Double> e) {
    // update();
    // }
    //
    // @UiHandler("startAngle")
    // public void onStartAngle(final ValueChangeEvent<Double> e) {
    // update();
    // }
    //
    // @UiHandler("endAngle")
    // public void onEndAngle(final ValueChangeEvent<Double> e) {
    // update();
    // }
    //
    // /**
    // *
    // */
    // private void update() {
    // if (innerRadius.getValue() != null) {
    // arc.innerRadius(innerRadius.getValue());
    // }
    // if (outerRadius.getValue() != null) {
    // arc.outerRadius(outerRadius.getValue());
    // }
    // if (startAngle.getValue() != null) {
    // arc.startAngle(startAngle.getValue());
    // }
    // if (endAngle.getValue() != null) {
    // arc.endAngle(endAngle.getValue());
    // }
    // svg.select("path")
    // .attr("d", arc);
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see com.github.gwtd3.demo.client.DemoCase#start()
    // */
    // @Override
    // public void start() {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // /*
    // * (non-Javadoc)
    // *
    // * @see com.github.gwtd3.demo.client.DemoCase#stop()
    // */
    // @Override
    // public void stop() {
    // // TODO Auto-generated method stub
    //
    // }
    //
    // /**
    // * @return
    // */
    // public static Factory factory() {
    // return new Factory() {
    // @Override
    // public DemoCase newInstance() {
    // return new ArcDemo();
    // }
    // };
    // }
    
    //#end region

}
