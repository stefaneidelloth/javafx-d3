package org.treez.javafxd3.d3;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.treez.javafxd3.d3.D3;


@SuppressWarnings("javadoc")
public class D3Test extends AbstractTestCase {
	
	@Before
	public void setUp() {
		
		/*
		super.setUp(sandbox);
		sandbox.add(new Label("1"));
		sandbox.add(new Label("1"));
		sandbox.add(new Label("1"));
		
		*/
	}

	
	@Override	
	public void doTest() {
		// version
		version();
		shuffle();
	}

	/**
	 * 
	 */
	private void shuffle() {
		
		D3 d3 = new D3(webEngine);
		
		// for char array
		char[] a = { 'a', 'b', 'c', 'd' };
		d3.shuffle(a);
		boolean sorted = true;
		char previous = 0;
		for (char c : a) {
			if (c < previous) {
				sorted = false;
				break;
			}
			previous = c;
		}
		if (sorted) {
			fail("shuffle did not work");
		}
		// for list
		List<String> array = Arrays.asList("1", "2", "3", "4");
		d3.shuffle(array);
		assertNotSorted(array);
	}

	private <T extends Comparable<T>> void assertNotSorted(final Iterable<T> array) {
		T previous = null;
		for (T comparable : array) {
			// when one element found is not sorted
			if ((previous != null) && (comparable.compareTo(previous) < 0)) {
				return;
			}
			previous = comparable;
		}
		fail("shuffle did not work");
	}

	private <T extends Comparable<T>> void assertNotSorted(final T[] array) {
		T previous = null;
		for (T comparable : array) {
			// when one element found is not sorted
			if ((previous != null) && (comparable.compareTo(previous) < 0)) {
				return;
			}
			previous = comparable;
		}
		fail("shuffle did not work");
	}


	/**
	 * 
	 */
	private void version() {
		
		D3 d3 = new D3(webEngine);
		
		d3.version();
	}

}
