package org.treez.javafxd3.d3;

import java.util.Arrays;
import java.util.List;

public class D3Test extends AbstractTestCase {

	@Override
	public void doTest() {
		version();
		shuffle();
	}

	private void version() {
		
		String version = d3.version();
		System.out.println("Using D3 version " + version);
		assertEquals("3.5.9", version);
	}

	private void shuffle() {		

		// for char array
		char[] a = { 'a', 'b', 'c', 'd' };
		d3.shuffle(a);
		
		boolean isSorted = checkIfArrayIsStillSorted(a);
		if (isSorted) {
			fail("shuffle did not work");
		}
		
		// for list
		List<String> array = Arrays.asList("1", "2", "3", "4");
		d3.shuffle(array);
		assertNotSorted(array);
	}

	private boolean checkIfArrayIsStillSorted(char[] a) {
		boolean sorted = true;
		char previous = 0;
		for (char c : a) {
			if (c < previous) {
				sorted = false;
				break;
			}
			previous = c;
		}
		return sorted;
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

}
