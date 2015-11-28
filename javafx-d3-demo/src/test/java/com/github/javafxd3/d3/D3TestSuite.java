package com.github.javafxd3.d3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javafxd3.d3.color.HslColorTest;
import com.github.javafxd3.d3.core.FormatTest;
import com.github.javafxd3.d3.core.TransformTest;
import com.github.javafxd3.d3.core.ValueTest;
import com.github.javafxd3.d3.dsv.DsvTest;
import com.github.javafxd3.d3.scales.IdentityScaleTest;
import com.github.javafxd3.d3.scales.LinearScaleTest;
import com.github.javafxd3.d3.scales.LogScaleTest;
import com.github.javafxd3.d3.scales.OrdinalScaleTest;
import com.github.javafxd3.d3.scales.PowScaleTest;
import com.github.javafxd3.d3.scales.QuantileScaleTest;
import com.github.javafxd3.d3.scales.QuantizeScaleTest;
import com.github.javafxd3.d3.scales.ThresholdScaleTest;
import com.github.javafxd3.d3.scales.TimeScaleTest;
import com.github.javafxd3.d3.svg.AreaTest;
import com.github.javafxd3.d3.svg.AxisTest;
import com.github.javafxd3.d3.svg.ChordTest;
import com.github.javafxd3.d3.svg.LineTest;
import com.github.javafxd3.d3.svg.SymbolTest;
import com.github.javafxd3.d3.behaviors.ZoomTest;
import com.github.javafxd3.d3.selection.SelectionAttrTest;
import com.github.javafxd3.d3.selection.SelectionClassedTest;
import com.github.javafxd3.d3.selection.SelectionContentsTest;
import com.github.javafxd3.d3.selection.SelectionControlsTest;
import com.github.javafxd3.d3.selection.SelectionData2Test;
import com.github.javafxd3.d3.selection.SelectionDataTest;
import com.github.javafxd3.d3.selection.SelectionHtmlTest;
import com.github.javafxd3.d3.selection.SelectionPropertyTest;
import com.github.javafxd3.d3.selection.SelectionStyleTest;
import com.github.javafxd3.d3.selection.SelectionTextTest;
import com.github.javafxd3.d3.selection.SubselectionsTest;
import com.github.javafxd3.d3.transition.EasingTest;
import com.github.javafxd3.d3.transition.InterpolatorsTest;
import com.github.javafxd3.d3.transition.TransitionTest;
import com.github.javafxd3.d3.tsv.TsvTest;


/**
 * 
 * 
 */
public class D3TestSuite {
	
	//#region ATTRIBUTES

    List<AbstractTestCase> tests = new ArrayList<AbstractTestCase>();
    
    //#end region 
    
    //#region METHODS

    /**
     * @return
     */
    public static D3TestSuite get() {
	D3TestSuite suite = new D3TestSuite();
	suite.tests = Arrays.asList(
			
		// arrays
		//new ArraysTest(),
		//new D3ArraysTest(),
		
		// utils
		new ValueTest(),
		
		// D3
		new D3Test(),
		new HslColorTest(),
		
		// selections
		new SubselectionsTest(),
		new SelectionContentsTest(),
		new SelectionAttrTest(),
		new SelectionClassedTest(),
		new SelectionDataTest(),
		new SelectionData2Test(),
		new SelectionPropertyTest(),
		new SelectionTextTest(),
		new SelectionHtmlTest(),
		new SelectionControlsTest(),
		new SelectionStyleTest(),
		
		// Transitions
		new TransitionTest(),
		new InterpolatorsTest(),
		new EasingTest(),
		
		// Math
		new TransformTest(),
		
		// Format
		new FormatTest(),
		
		// Scales
		new LinearScaleTest(), new LogScaleTest(), new PowScaleTest(),
		new IdentityScaleTest(), new ThresholdScaleTest(),
		new QuantizeScaleTest(), new QuantileScaleTest(),
		new OrdinalScaleTest(),
		new TimeScaleTest(),
		
		// svg
		new AxisTest(), new LineTest(), new AreaTest(),
		new SymbolTest(), new ChordTest(),
		
		// time
		//new TimeFormatTest(), new TimeIntervalsTest(),
		//new TimeScaleTest(),
		
		// requests
		new DsvTest(), new TsvTest(),

		// behaviors
		new ZoomTest());
	return suite;
    }
    
    //#end region
    
    //#region ACCESSORS

    /**
     * @return the tests
     */
    public List<AbstractTestCase> getTests() {
    	return tests;
    }
    
    //#end region  
    
    
}
