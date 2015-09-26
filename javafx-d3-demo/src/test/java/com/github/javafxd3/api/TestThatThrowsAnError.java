package com.github.javafxd3.api;

/**
 * 
 * 
 */
public class TestThatThrowsAnError extends AbstractTestCase {

	
	/**
	 * 
	 */
	@Override
	public void doTest() {
		throw new NullPointerException("fake NPE to test");
	}

}
