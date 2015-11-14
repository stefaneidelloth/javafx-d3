package com.github.javafxd3.api.selection;



import java.util.Arrays;

import org.junit.Test;

import com.github.javafxd3.api.arrays.Array;
import com.github.javafxd3.api.core.Selection;
import com.github.javafxd3.api.core.Value;
import com.github.javafxd3.api.functions.DatumFunction;
import com.github.javafxd3.api.functions.KeyFunction;
import com.github.javafxd3.api.wrapper.Element;

@SuppressWarnings("javadoc")
public class SelectionDataTest extends AbstractSelectionTest {

	@Override
	@Test
	public void doTest() {
		testSelectionDataGetter();
		testSelectionDataSetterArray();
		testSelectionDataSetterFunctionReturningJSO();
		testSelectionDataSetterArrayWithKeyFunction();

		testNestedSelection();
	}

	private final int[][] MATRIX = new int[][] { { 11975, 5871, 8916, 2868 },
			{ 1951, 10048, 2060, 6171 }, { 8010, 16145, 8090, 8045 },
			{ 1013, 990, 940, 6907 } };

	protected Selection givenASimpleSelection() {
		clearSvg();
		return d3.select("root").append("table").selectAll("tr")
				.data(MATRIX[0]).enter().append("tr");
	}

	/**
	 * Create a nested selection of td elements grouped by tr elements inside a
	 * root table element.
	 * <p>
	 * The td elements are joined with the MATRIX.
	 * 
	 * @return the selection
	 */
	protected Selection givenANestedSelection() {
		clearSvg();
		Selection tr = d3.select("root").append("table").selectAll("tr").data(MATRIX).enter().append("tr");

		Selection td = tr.selectAll("td").data(new DatumFunction<Integer[]>() {
					@Override
					public Integer[] apply(final Object context,
							final Object d, final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						Integer[] as = datum.as();
						return as;
					}
				}).enter().append("td").text(new DatumFunction<String>() {
					@Override
					public String apply(final Object context, final Object d,
							final int index) {
						
						Value datum = (Value) d;						
						Element element =(Element) context;
						
						return datum.asString();
					}
				});
		return td;
	}

	/**
	 * 
	 */
	private void testSelectionDataSetterFunctionReturningJSO() {
		Selection selection = givenTrElementsInATable(3);

		selection.data(new DatumFunction<String[]>() {
			@Override
			public String[] apply(final Object context, final Object d,
					final int index) {
				System.out
						.println("testSelectionDataSetterFunctionReturningPrimitiveArray "
								+ index);
				return new String[]{"0", "1", "2"};
			}
		});

		assertDataPropertyEqualsTo("0", selection, 0);
		assertDataPropertyEqualsTo("1", selection, 1);
		assertDataPropertyEqualsTo("2", selection, 2);

	}

	protected Selection givenTrElementsInATable(final int numberOfTRToCreate) {
		clearSvg();
		Selection table = d3.select("root").append("table");
		for (int i = 0; i < numberOfTRToCreate; i++) {
			table.append("tr");
		}
		Selection selection = table.selectAll("tr");
		assertEquals(numberOfTRToCreate, selection.size());
		return selection;

	}

	/**
	 * 
	 */
	private void testSelectionDataSetterArray() {
		Selection selection = givenTrElementsInATable(3);
		// bytes
		selection.data(new byte[] { 60, 5, 100 });
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);
		// double
		selection.data(new double[] { 61.0, 6.0, 101.0 });
		assertDataPropertyEqualsTo(61, selection, 0);
		assertDataPropertyEqualsTo(6, selection, 1);
		assertDataPropertyEqualsTo(101, selection, 2);

		// float
		selection.data(new float[] { 62.0f, 7.0f, 102.0f });
		assertDataPropertyEqualsTo(62, selection, 0);
		assertDataPropertyEqualsTo(7, selection, 1);
		assertDataPropertyEqualsTo(102, selection, 2);

		// int
		selection.data(new int[] { 63, 8, 103 });
		assertDataPropertyEqualsTo(63, selection, 0);
		assertDataPropertyEqualsTo(8, selection, 1);
		assertDataPropertyEqualsTo(103, selection, 2);

		// long
		// selection.data(new long[] { 64l, 9l, 104l });
		// assertDataPropertyEqualsTo(64, selection, 0);
		// assertDataPropertyEqualsTo(9, selection, 1);
		// assertDataPropertyEqualsTo(104, selection, 2);

		// short
		selection.data(new short[] { 65, 10, 105 });
		assertDataPropertyEqualsTo(65, selection, 0);
		assertDataPropertyEqualsTo(10, selection, 1);
		assertDataPropertyEqualsTo(105, selection, 2);

		// short
		selection.data(new short[] { 66, 11, 106 });
		assertDataPropertyEqualsTo(66, selection, 0);
		assertDataPropertyEqualsTo(11, selection, 1);
		assertDataPropertyEqualsTo(106, selection, 2);

		// Object
		selection.data(new Object[] { "67", "12", "107" });
		assertDataPropertyEqualsTo("67", selection, 0);
		assertDataPropertyEqualsTo("12", selection, 1);
		assertDataPropertyEqualsTo("107", selection, 2);

		// JSO
		selection.data(new char[]{'b', 'z', 'g'});
		assertDataPropertyEqualsTo('b', selection, 0);
		assertDataPropertyEqualsTo('z', selection, 1);
		assertDataPropertyEqualsTo('g', selection, 2);

		// List<?>
		selection.data(new Object[]{68, 13, 108});
		assertDataPropertyEqualsTo(68, selection, 0);
		assertDataPropertyEqualsTo(13, selection, 1);
		assertDataPropertyEqualsTo(108, selection, 2);
	}

	private void testSelectionDataSetterArrayWithKeyFunction() {
		Selection selection = givenTrElementsInATable(3);
		selection.data(new byte[] { 60, 5, 100 });
		KeyFunction<Integer> func = new KeyFunction<Integer>() {
			@Override
			public Integer map(final Element context,
					final Object[] newDataArray, final Value datum,
					final int index) {
				return datum.asInt();
			}
		};

		// bytes
		selection.data(new byte[] { 60, 5, 100 }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);
		// double
		selection.data(new double[] { 5.0, 60.0, 100.0 }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// float
		selection.data(new float[] { 5.0f, 60.0f, 100.0f }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// int
		selection.data(new int[] { 60, 5, 100 }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// long
		selection.data(new long[] { 60l, 5l, 100l }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// short
		selection.data(new short[] { 60, 5, 100 }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// short
		selection.data(new short[] { 60, 5, 100 }, func);
		assertDataPropertyEqualsTo(60, selection, 0);
		assertDataPropertyEqualsTo(5, selection, 1);
		assertDataPropertyEqualsTo(100, selection, 2);

		// Object
		selection.data(new Object[] { "67", "12", "107" });
		selection.data(new Object[] { "67", "12", "107" }, func);
		assertDataPropertyEqualsTo("67", selection, 0);
		assertDataPropertyEqualsTo("12", selection, 1);
		assertDataPropertyEqualsTo("107", selection, 2);

		// JSO
		selection.data(new char[]{'b', 'z', 'g'});
		selection.data(new char[]{'b', 'z', 'g'}, func);
		assertDataPropertyEqualsTo('b', selection, 0);
		assertDataPropertyEqualsTo('z', selection, 1);
		assertDataPropertyEqualsTo('g', selection, 2);

		// List<?>
		selection.data(new Object[]{67, 13, 108});
		selection.data(Arrays.asList(67, 13, 108), func);
		assertDataPropertyEqualsTo(67, selection, 0);
		assertDataPropertyEqualsTo(13, selection, 1);
		assertDataPropertyEqualsTo(108, selection, 2);
	}

	protected void assertDataPropertyEqualsTo(final int expectedData,
			final Selection selection, final int elementIndex) {
		
		int propertyValue = selection.get(0).get(elementIndex).node().getPropertyInt(Selection.DATA_PROPERTY);
		assertEquals(expectedData, propertyValue);

	}

	protected void assertDataPropertyEqualsTo(final String expectedData,
			final Selection selection, final int elementIndex) {		
		assertEquals(expectedData, selection.get(0).get(elementIndex).node().getPropertyString(Selection.DATA_PROPERTY));
	}

	/**
	 * 
	 */
	private void testSelectionDataGetter() {
		// given a 2-dim selection
		Selection selection = givenANestedSelection();
		Array<Integer> data = selection.data();
		assertEquals(MATRIX[0][0], (int) data.get(0, Integer.class));
		assertEquals(MATRIX[0][1], (int) data.get(1, Integer.class));
		// with a single group selection
		selection = givenASimpleSelection();
		data = selection.data();
		assertEquals(MATRIX[0][0], (int) data.get(0, Integer.class));
		assertEquals(MATRIX[0][1], (int) data.get(1 , Integer.class));

	}

	private void testNestedSelection() {
		clearSvg();
		Selection tr = d3.select("root").append("table").selectAll("tr")
				.data(MATRIX).enter().append("tr");

		// mapping second level
		System.out.println("creating second level divs");
		Selection td = tr.selectAll("td").data(new DatumFunction<Object[]>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.github.gwtd3.api.functions.DatumFunction#apply(com.google
			 * .gwt.dom.client.Element, com.github.gwtd3.api.core.Datum, int)
			 */
			@Override
			public Object[] apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				System.out.println(context + " " + datum.asString() + " " + index);
				Object[] as = datum.as();
				System.out.println(as);
				return as;
				// return JsArrays.asJsArray(Arrays.asList("J", "K", "L"));
			}
		}).enter().append("td").text(new DatumFunction<String>() {
			@Override
			public String apply(final Object context, final Object d,
					final int index) {
				
				Value datum = (Value) d;						
				Element element =(Element) context;
				
				return datum.asString();
			}
		});

	}
}
