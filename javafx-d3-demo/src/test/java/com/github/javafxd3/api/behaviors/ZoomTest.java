package com.github.javafxd3.api.behaviors;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.behaviour.Zoom;
import com.github.javafxd3.api.behaviour.Zoom.ZoomEventType;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.wrapper.Element;

/**
 * Tests the class Zoom   
 */
public class ZoomTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	@Test
	public void doTest() {
		testCreate();
	}

	private void testCreate() {
		
		D3 d3 = new D3(webEngine);
		
		Zoom zoom = d3.behavior().zoom();

		assertNull(zoom.center());
		zoom.center(5, 6);
		assertNotNull(zoom.center());

		assertEquals(960.0, zoom.size()[0], DELTA);
		assertEquals(500.0, zoom.size()[1], DELTA);
		zoom.size(400, 300);
		assertEquals(400.0, zoom.size()[0], DELTA);
		assertEquals(300.0, zoom.size()[1], DELTA);

		zoom.event(d3.select("body"));
		zoom.event(d3.select("body").transition());

		zoom.on(ZoomEventType.ZOOMSTART, noopListener);
		zoom.on(ZoomEventType.ZOOM, noopListener);
		zoom.on(ZoomEventType.ZOOMEND, noopListener);

		zoom.scale();
		zoom.scale(5.0);

		zoom.scaleExtent();
		zoom.scaleExtent(new Double[]{5.0, 4.0});
		zoom.translate();
		zoom.translate(new Double[]{5.0, 6.0});

	}

	private final DatumFunction<Void> noopListener = new DatumFunction<Void>() {
		@Override
		public Void apply(Object context, Object d, int index) {

			return null;
		};
	};

}
