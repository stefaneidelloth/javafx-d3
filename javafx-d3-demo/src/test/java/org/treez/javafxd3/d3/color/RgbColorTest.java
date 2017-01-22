
package org.treez.javafxd3.d3.color;

import org.treez.javafxd3.d3.color.Colors;
import org.treez.javafxd3.d3.color.HSLColor;
import org.treez.javafxd3.d3.color.RGBColor;

import org.treez.javafxd3.d3.AbstractTestCase;

/**
 * Tests the class RgbColor
 */
public class RgbColorTest extends AbstractTestCase {

	@Override
	public void doTest() {

		Colors colors = new Colors(engine);

		RGBColor rgb = colors.rgb("#ff0000");
		assertEquals(255, rgb.r());
		assertEquals(0, rgb.g());
		assertEquals(0, rgb.b());
		rgb = rgb.darker();
		assertTrue(rgb.r() < 255);
		rgb = rgb.brighter().brighter();
		assertEquals(255, rgb.r());
		HSLColor hsl = rgb.hsl();
		System.out.println(hsl.h());
		System.out.println(hsl.s());
		System.out.println(hsl.l());

		RGBColor rgb2 = colors.rgb(0, 0, 255);
		assertEquals(0, rgb2.r());
		assertEquals(0, rgb2.g());
		assertEquals(255, rgb2.b());
		System.out.println(rgb2.toHexaString());

		RGBColor rgb3 = colors.rgb(hsl);
		assertEquals(255, rgb3.r());
		assertEquals(0, rgb3.g());
		assertEquals(0, rgb3.b());
	}

}
