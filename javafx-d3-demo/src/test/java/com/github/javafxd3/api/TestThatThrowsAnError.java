package com.github.javafxd3.api;

import org.junit.Test;

/**
 * 
 * 
 */
public class TestThatThrowsAnError extends AbstractTestCase {

	
	/**
	 * 
	 */
	@Override
	@Test
	public void doTest() {
		throw new NullPointerException("fake NPE to test");
	}

}
