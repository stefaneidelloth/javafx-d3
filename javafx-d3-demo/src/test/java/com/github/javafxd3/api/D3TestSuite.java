package com.github.javafxd3.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javafxd3.api.behaviors.ZoomTest;
import com.github.javafxd3.api.color.HslColorTest;
import com.github.javafxd3.api.core.FormatTest;
import com.github.javafxd3.api.core.TransformTest;
import com.github.javafxd3.api.core.ValueTest;
import com.github.javafxd3.api.dsv.DsvTest;
import com.github.javafxd3.api.scales.IdentityScaleTest;
import com.github.javafxd3.api.scales.LinearScaleTest;
import com.github.javafxd3.api.scales.LogScaleTest;
import com.github.javafxd3.api.scales.OrdinalScaleTest;
import com.github.javafxd3.api.scales.PowScaleTest;
import com.github.javafxd3.api.scales.QuantileScaleTest;
import com.github.javafxd3.api.scales.QuantizeScaleTest;
import com.github.javafxd3.api.scales.ThresholdScaleTest;
import com.github.javafxd3.api.scales.TimeScaleTest;
import com.github.javafxd3.api.selection.SelectionAttrTest;
import com.github.javafxd3.api.selection.SelectionClassedTest;
import com.github.javafxd3.api.selection.SelectionContentsTest;
import com.github.javafxd3.api.selection.SelectionControlsTest;
import com.github.javafxd3.api.selection.SelectionData2Test;
import com.github.javafxd3.api.selection.SelectionDataTest;
import com.github.javafxd3.api.selection.SelectionHtmlTest;
import com.github.javafxd3.api.selection.SelectionPropertyTest;
import com.github.javafxd3.api.selection.SelectionStyleTest;
import com.github.javafxd3.api.selection.SelectionTextTest;
import com.github.javafxd3.api.selection.SubselectionsTest;
import com.github.javafxd3.api.svg.AreaTest;
import com.github.javafxd3.api.svg.AxisTest;
import com.github.javafxd3.api.svg.ChordTest;
import com.github.javafxd3.api.svg.LineTest;
import com.github.javafxd3.api.svg.SymbolTest;
import com.github.javafxd3.api.transition.EasingTest;
import com.github.javafxd3.api.transition.InterpolatorsTest;
import com.github.javafxd3.api.transition.TransitionTest;
import com.github.javafxd3.api.tsv.TsvTest;


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
