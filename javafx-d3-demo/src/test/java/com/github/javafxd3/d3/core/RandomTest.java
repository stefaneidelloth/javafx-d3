package com.github.javafxd3.d3.core;

import com.github.javafxd3.d3.AbstractTestCase;
import com.github.javafxd3.d3.core.Random;

/**
 * Tests the class Random
 */
public class RandomTest extends AbstractTestCase {

	@Override
	public void doTest() {
		Random random = new Random(webEngine);

		random.normal().generate();
		random.normal(1000).generate();
		random.normal(1000, 40).generate();

		random.logNormal().generate();
		random.logNormal(1000).generate();
		random.logNormal(1000, 40).generate();

		random.irwinHall(456).generate();
	}

}
