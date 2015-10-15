package com.github.javafxd3.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Prefix;

/**
 * Thest the format method of the d3 wrapper
 *
 */
public class FormatTest extends AbstractTestCase {

	

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		d3.format("%s").format(456.34);
		assertEquals(123.12,d3.round(123.1234,2),1e-6);
		assertEquals("This is a 'quoted' string, yes \\? And the dots, like \\. are counted as \\{literals\\}",
				d3.requote("This is a 'quoted' string, yes ? And the dots, like . are counted as {literals}"));
		Prefix prefix = d3.formatPrefix(123456.12345, 1);
		assertEquals("k",prefix.symbol());
		assertEquals(1000.0,prefix.scale(1000000),1e-6);
		
	}
}
