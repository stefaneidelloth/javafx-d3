package org.treez.javafxd3.d3;

/** 
 * 
 */
public class TestThatThrowsAnError extends AbstractTestCase {

	@Override
	public void doTest() {
		throw new NullPointerException("fake NPE to test");
	}
}
