package org.treez.javafxd3.d3.scales;

import org.treez.javafxd3.d3.D3;
import org.treez.javafxd3.d3.core.Value;
import org.treez.javafxd3.d3.scales.OrdinalScale;

import org.treez.javafxd3.d3.AbstractTestCase;

@SuppressWarnings("javadoc")
public class OrdinalScaleTest extends AbstractTestCase {

	@Override	
    public void doTest() {
    	
    	D3 d3 = new D3(webEngine);

        // new ordinal scale is undefined
    	Value value = d3.scale().ordinal().apply(10);
    	boolean isUndefined = value.isUndefined();
        assertTrue(isUndefined);

        testColorPalettes();

        // copy
        OrdinalScale ordinal = d3.scale().ordinal();
        ordinal.domain(1, 2);
        OrdinalScale copy = ordinal.copy();
        copy.domain(2, 3);
        assertEquals(1.0, ordinal.domain().get(0, Double.class),TOLERANCE);
        assertEquals(2.0, ordinal.domain().get(1, Double.class),TOLERANCE);

        OrdinalScale scale = d3.scale().ordinal();
        scale.domain((byte) 0, (byte) 10);
        scale.domain(0f, 10f);
        scale.domain(0d, 10d);
        scale.domain(0, 10);
        scale.domain(0l, 10l);
        scale.domain((short) 0, (short) 10);
        scale.domain("0", "10");

        // range
        scale.range((byte) 1, (byte) 2, (byte) 3);
        scale.range(1d, 2d, 3d);
        scale.range(1f, 2f, 3f);
        scale.range(1, 2, 3);
        scale.range(1l, 2l, 3l);
        scale.range((short) 1, (short) 2, (short) 3);
        scale.range("1", "2");
        scale.range();
        // range round
        scale.rangeBands(0, 100);
        scale.rangeBands(0, 100, 0.5);
        scale.rangeBands(0, 100, 0.5, 0.6);

        scale.rangePoints(50, 60, 1);
        scale.rangePoints(50, 60);

        scale.rangeRoundPoints(50, 60, 1);
        scale.rangeRoundPoints(50, 60);

        scale.rangeRoundBands(0, 100);
        scale.rangeRoundBands(0, 100, 0.5);
        scale.rangeRoundBands(0, 100, 0.5, 0.6);

        scale.rangeExtent();

        scale.rangeBand();

        // behaviour

        OrdinalScale ordinal2 = d3.scale().ordinal()
                .rangeBands(0, 1000, 0.1, 0.1);
        ordinal2.domain("01/01", "01/02", "01/03", "01/04", "01/05");

        System.out.println(ordinal2.apply("01/01").asInt());
        System.out.println(ordinal2.apply("01/02").asInt());
        System.out.println(ordinal2.apply("01/03").asInt());
        System.out.println(ordinal2.apply("01/04").asInt());
        System.out.println(ordinal2.apply("01/05").asInt());
        System.out.println(ordinal2.rangeBand());

        assertEquals(19, (int) ordinal2.apply("01/01").asInt());
        assertEquals(215, (int) ordinal2.apply("01/02").asInt());
        assertEquals(411, (int) ordinal2.apply("01/03").asInt());
        assertEquals(607, (int) ordinal2.apply("01/04").asInt());
        assertEquals(803, (int) ordinal2.apply("01/05").asInt());

        assertEquals(176.47, ordinal2.rangeBand(), 0.005);

    }

    private void testColorPalettes() {
    	
    	D3 d3 = new D3(webEngine);
    	
        assertColorPalettes(d3.scale().category10(),
                "#1f77b4 #ff7f0e #2ca02c #d62728 #9467bd #8c564b #e377c2 #7f7f7f #bcbd22 #17becf");
        assertColorPalettes(
                d3.scale().category20(),
                "#1f77b4 #aec7e8 #ff7f0e #ffbb78 #2ca02c #98df8a #d62728 #ff9896 #9467bd #c5b0d5 #8c564b #c49c94 #e377c2 #f7b6d2 #7f7f7f #c7c7c7 #bcbd22 #dbdb8d #17becf #9edae5");
        assertColorPalettes(
                d3.scale().category20b(),
                "#393b79 #5254a3 #6b6ecf #9c9ede #637939 #8ca252 #b5cf6b #cedb9c #8c6d31 #bd9e39 #e7ba52 #e7cb94 #843c39 #ad494a #d6616b #e7969c #7b4173 #a55194 #ce6dbd #de9ed6");
        assertColorPalettes(
                d3.scale().category20c(),
                "#3182bd #6baed6 #9ecae1 #c6dbef #e6550d #fd8d3c #fdae6b #fdd0a2 #31a354 #74c476 #a1d99b #c7e9c0 #756bb1 #9e9ac8 #bcbddc #dadaeb #636363 #969696 #bdbdbd #d9d9d9");
    }

    private void assertColorPalettes(final OrdinalScale scale, final String colorString) {
        String[] expectedColors = colorString.split(" ");
        int i = 0;
        for (String color : expectedColors) {
            assertEquals(color, scale.apply(i++).asString());
        }
    }
}
