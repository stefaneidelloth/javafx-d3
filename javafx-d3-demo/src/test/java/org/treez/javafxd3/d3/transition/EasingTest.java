package org.treez.javafxd3.d3.transition;

import java.util.Random;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.ease.Easing;
import org.treez.javafxd3.d3.ease.EasingFunction;
import org.treez.javafxd3.d3.ease.Mode;

import org.treez.javafxd3.d3.AbstractTestCase;
import org.treez.javafxd3.d3.transition.function.CustomEasingFunction;

@SuppressWarnings("javadoc")
public class EasingTest extends AbstractTestCase {
	
	private static final double DELTA = 0.001d;
		
	@Override	
	public void doTest() {		

		// built in
		testEasingFunction(Easing.back(webEngine, 4), Easing.back(webEngine,Mode.IN_OUT, 4));
		testEasingFunction(Easing.bounce(webEngine), Easing.bounce(webEngine,Mode.IN_OUT));
		testEasingFunction(Easing.circle(webEngine), Easing.circle(webEngine,Mode.OUT));
		testEasingFunction(Easing.cubic(webEngine), Easing.cubic(webEngine,Mode.IN_OUT));
		testEasingFunction(Easing.exp(webEngine), Easing.exp(webEngine,Mode.OUT_IN));
		testEasingFunction(Easing.linear(webEngine), Easing.linear(webEngine,Mode.IN_OUT));
		testEasingFunction(Easing.quad(webEngine), Easing.quad(webEngine,Mode.IN_OUT));
		testEasingFunction(Easing.sin(webEngine), Easing.sin(webEngine,Mode.IN_OUT));
		testEasingFunction(Easing.elastic(webEngine,10, 0.5), Easing.elastic(webEngine,Mode.IN_OUT, 10, 0.5));
		testEasingFunction(Easing.poly(webEngine,3), Easing.poly(webEngine,Mode.IN_OUT, 3));

		// custom
		EasingFunction f = new CustomEasingFunction();
		testEasingFunction(f, f);

		// pass it
	}

	protected void testEasingFunction(final EasingFunction e1, final EasingFunction e2) {
		
		D3 d3 = new D3(webEngine);
		
		// test the extreme values
		assertEquals(0.0, e1.ease(0),DELTA);
		assertEquals(1.0, e1.ease(1),DELTA);

		assertEquals(0.0, e2.ease(0),DELTA);
		assertEquals(1.0, e2.ease(1),DELTA);
		
		Random random = new Random();

		Selection selection = d3.select("svg").append("div");
		// pass it to Transition.ease1
		//		d3.select(sandbox).append("div")
		//		.attr("foo", "stuff")
		selection.transition().duration(1000).ease(e1)
		.attr("foobar", Integer.toString(random.nextInt()))
		.transition()
		.ease(e2)
		.attr("foobar", Integer.toString(random.nextInt()));
	}
}
