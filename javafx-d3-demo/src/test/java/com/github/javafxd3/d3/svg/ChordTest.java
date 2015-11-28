package com.github.javafxd3.d3.svg;

import com.github.javafxd3.d3.AbstractTestCase;
import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.svg.Chord;
import com.github.javafxd3.d3.svg.datumfunction.ChordRadiusDatumFunction;
import com.github.javafxd3.d3.svg.datumfunction.ChordSourceDatumFunction;
import com.github.javafxd3.d3.svg.datumfunction.ChordStartAngleDatumFunction;
import com.github.javafxd3.d3.svg.datumfunction.ChordTargetDatumFunction;


@SuppressWarnings("javadoc")
public class ChordTest extends AbstractTestCase {

	@Override	
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		Chord chord = d3.svg().chord();

		// constants
		chord.startAngle(5);
		chord.endAngle(5);
		chord.radius(6);

		chord.source(new ChordSourceDatumFunction());
		chord.target(new ChordTargetDatumFunction());
		
		// chord
		chord.startAngle(new ChordStartAngleDatumFunction(webEngine)) //
		.endAngle(new ChordStartAngleDatumFunction(webEngine)) //
		.radius(new ChordRadiusDatumFunction(webEngine));

		chord.generate(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
	}

	
}
