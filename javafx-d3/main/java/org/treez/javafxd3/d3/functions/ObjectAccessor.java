package org.treez.javafxd3.d3.functions;

import org.treez.javafxd3.d3.dsv.DsvRow;

public interface ObjectAccessor<A, R> {
	
    /**
     * The accessor function which transform a {@link DsvRow} in an other type
     * T.
     * 
     * @param row
     *            the current DSV row
     * @param index
     *            index of the current DSV row
     * @return
     */
    R apply(A data, int index);
}
