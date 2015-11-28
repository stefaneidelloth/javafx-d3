package com.github.javafxd3.d3.core;

import com.github.javafxd3.d3.dsv.DsvRow;

public interface ObjectAccessor<D, T> {
	
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
    T apply(D data, int index);
}
