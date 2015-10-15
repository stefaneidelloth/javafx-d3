package com.github.javafxd3.api;

import org.junit.Test;

import javafx.scene.Node;

/**
 * 
 * 
 */
public class TestThatFails extends AbstractTestCase {

	@Override
	@Test
	public void doTest() {
		throw new AssertionError("fake assertionerror to test");
	}

}
