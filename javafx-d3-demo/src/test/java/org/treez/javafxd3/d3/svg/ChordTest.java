package org.treez.javafxd3.d3.svg;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.svg.datafunction.ChordRadiusDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.ChordSourceDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.ChordStartAngleDataFunction;
import org.treez.javafxd3.d3.svg.datafunction.ChordTargetDataFunction;


@SuppressWarnings("javadoc")
public class ChordTest extends AbstractTestCase {

	@Override	
	public void doTest() {
		
		
		
		Chord chord = d3.svg().chord();

		// constants
		chord.startAngle(5);
		chord.endAngle(5);
		chord.radius(6);

		chord.source(new ChordSourceDataFunction());
		chord.target(new ChordTargetDataFunction());
		
		// chord
		chord.startAngle(new ChordStartAngleDataFunction(engine)) //
		.endAngle(new ChordStartAngleDataFunction(engine)) //
		.radius(new ChordRadiusDataFunction(engine));

		chord.generate(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
	}

	
}
