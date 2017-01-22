package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.core.Random;

import org.treez.javafxd3.d3.AbstractTestCase;

/**
 * Tests the class Random
 */
public class RandomTest extends AbstractTestCase {

	@Override
	public void doTest() {
		Random random = new Random(engine);

		random.normal().generate();
		random.normal(1000).generate();
		random.normal(1000, 40).generate();

		random.logNormal().generate();
		random.logNormal(1000).generate();
		random.logNormal(1000, 40).generate();

		random.irwinHall(456).generate();
	}

}
