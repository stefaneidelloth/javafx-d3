package org.treez.javafxd3.d3.behaviors;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.behaviour.Zoom;
import org.treez.javafxd3.d3.behaviour.Zoom.ZoomEventType;
import org.treez.javafxd3.d3.core.Selection;
import org.treez.javafxd3.d3.functions.DatumFunction;

import org.treez.javafxd3.d3.AbstractTestCase;

/**
 * Tests the class Zoom
 */
public class ZoomTest extends AbstractTestCase {

	private static final double DELTA = 0.001d;

	@Override
	public void doTest() {
		D3 d3 = new D3(webEngine);

		Zoom zoom = d3.behavior().zoom();

		assertNull(zoom.center());
		zoom.center(5, 6);
		assertNotNull(zoom.center());

		assertEquals(960.0, zoom.size().get(0, Double.class), DELTA);
		assertEquals(500.0, zoom.size().get(1, Double.class), DELTA);
		zoom.size(400, 300);
		assertEquals(400.0, zoom.size().get(0, Double.class), DELTA);
		assertEquals(300.0, zoom.size().get(1, Double.class), DELTA);

		Selection body = d3.select("body");

		zoom.event(body);
		zoom.event(body.transition());

		zoom.on(ZoomEventType.ZOOMSTART, noopListener);
		zoom.on(ZoomEventType.ZOOM, noopListener);
		zoom.on(ZoomEventType.ZOOMEND, noopListener);

		zoom.scale();
		zoom.scale(5.0);

		zoom.scaleExtent();
		zoom.scaleExtent(new Double[] { 5.0, 4.0 });
		zoom.translate();
		zoom.translate(new Double[] { 5.0, 6.0 });
	}

	private final DatumFunction<Void> noopListener = new DatumFunction<Void>() {
		@Override
		public Void apply(Object context, Object d, int index) {

			return null;
		};
	};

}
