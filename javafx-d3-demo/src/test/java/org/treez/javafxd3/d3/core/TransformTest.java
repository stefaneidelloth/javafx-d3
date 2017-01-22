package org.treez.javafxd3.d3.core;

import org.treez.javafxd3.d3.core.Transform;

import org.treez.javafxd3.d3.AbstractTestCase;

/**
 * Tests the Transform class
 *
 */
public class TransformTest extends AbstractTestCase {

	@Override
	public void doTest() {

		Transform transform = new Transform(engine);

		// empty
		Transform tr = transform.parse("");
		assertEquals("translate(0,0)rotate(0)skewX(0)scale(1,1)", tr.toString());

		// rotate
		tr = transform.parse("rotate(45)");
		assertEquals(45.0, tr.rotate(), 0.0001d);
		tr.rotate(60);
		assertEquals(60.0, tr.rotate(), 0.0001d);
		assertTrue(tr.toString().contains("rotate(60)"));

		// scale
		tr = transform.parse("scale(2,3)");
		assertEquals(2d, tr.scale().get(0, Double.class), 0.0001d);
		assertEquals(3d, tr.scale().get(1, Double.class), 0.0001d);
		tr.scale(5);
		assertEquals(5d, tr.scale().get(0, Double.class), 0.0001d);
		assertEquals(0d, tr.scale().get(1, Double.class), 0.0001d);
		assertTrue(tr.toString().contains("scale(5,0)"));

		// translate
		tr = transform.parse("translate(10,15)");
		assertEquals(10d, tr.translate().get(0, Double.class), 0.0001d);
		assertEquals(15d, tr.translate().get(1, Double.class), 0.0001d);
		tr.translate(16, 17);
		assertEquals(16d, tr.translate().get(0, Double.class), 0.0001d);
		assertEquals(17d, tr.translate().get(1, Double.class), 0.0001d);
		assertTrue(tr.toString().contains("translate(16,17)"));

		// translate
		tr = transform.parse("skewX(45)");
		assertEquals(45d, tr.skew(), 0.0001d);
		tr.skew(60);
		assertEquals(60d, tr.skew(), 0.0001d);
		assertTrue(tr.toString().contains("skewX(60)"));

		// mixed
		tr = transform.parse("translate(30,50)rotate(24)skewX(47)scale(12,16)");
		assertEquals(47d, tr.skew(), 0.0001d);
		assertEquals(24d, tr.rotate(), 0.0001d);
		assertEquals(30d, tr.translate().get(0, Double.class), 0.0001d);
		assertEquals(50d, tr.translate().get(1, Double.class), 0.0001d);
		assertEquals(12d, tr.scale().get(0, Double.class), 0.0001d);
		assertEquals(16d, tr.scale().get(1, Double.class), 0.0001d);
	}

}
