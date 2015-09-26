package com.github.javafxd3.api;

import javafx.scene.Node;

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
