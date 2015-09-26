package com.github.javafxd3.api.svg;

import java.sql.Array;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;


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

		chord.source(new DatumFunction<ChordDef>() {
			@Override
			public ChordDef apply(Element context, Value d, int index) {
				System.out.println("source" + d);
				return new ChordDef(index * 5, index * 5, index * 5);
			}
		});
		chord.target(new DatumFunction<ChordDef>() {
			@Override
			public ChordDef apply(Element context, Value d, int index) {
				System.out.println("target" + d);
				return new ChordDef(index * 5, index * 5, index * 5);
			}
		});
		
		// chord
		chord.startAngle(new DatumFunction<Double>() {
			@Override
			public Double apply(Element context, Value d, int index) {
				System.out.println("start" + d);
				return d.<ChordDef> as().start;
			}
		}).endAngle(new DatumFunction<Double>() {
			@Override
			public Double apply(Element context, Value d, int index) {
				System.out.println("end" + d);
				return d.<ChordDef> as().end;
			}
		}).radius(new DatumFunction<Double>() {
			@Override
			public Double apply(Element context, Value d, int index) {
				System.out.println("rad" + d);
				return d.<ChordDef> as().radius;
			}
		});

		//
		chord.generate(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
	}

	public static class ChordDef {
		double start;
		double end;
		double radius;

		public ChordDef(double start, double end, double radius) {
			super();
			this.start = start;
			this.end = end;
			this.radius = radius;
		}

	}
}
