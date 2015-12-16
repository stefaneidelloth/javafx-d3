package org.treez.javafxd3.d3;

/**
 * 
 * 
 */
public class TestThatFails extends AbstractTestCase {

	@Override	
	public void doTest() {
		throw new AssertionError("fake assertionerror to test");
	}

}
