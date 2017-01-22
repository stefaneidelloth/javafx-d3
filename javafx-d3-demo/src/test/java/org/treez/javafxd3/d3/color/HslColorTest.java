package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.color.HSLColor;
import org.treez.javafxd3.d3.color.RGBColor;

import org.treez.javafxd3.d3.AbstractTestCase;

/**
 * Tests the class HslColor
 */
public class HslColorTest extends AbstractTestCase {

	@Override
	public void doTest() {
		Colors colors = new Colors(engine);

		HSLColor hsl = colors.hsl("red");
		assertEquals(0.0, hsl.h(), 1e-6);
		assertEquals(1.0, hsl.s(), 1e-6);
		assertEquals(0.5, hsl.l(), 1e-6);
		hsl = hsl.darker();
		assertTrue(hsl.h() < 255);
		hsl = hsl.brighter();
		assertEquals(0.0, hsl.h(), TOLERANCE);
		RGBColor rgb = hsl.rgb();
		assertEquals(255, rgb.r());
		assertEquals(0, rgb.g());
		assertEquals(0, rgb.b());

		HSLColor hsl2 = colors.hsl(120, 1, 0.5);
		System.out.println(hsl2.toHexaString());

		HSLColor hsl3 = colors.hsl(rgb);
		assertEquals(0.0, hsl3.h(), 1e-6);
		assertEquals(1.0, hsl3.s(), 1e-6);
		assertEquals(0.5, hsl3.l(), 1e-6);
	}

}
