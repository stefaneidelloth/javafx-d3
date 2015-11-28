package com.github.javafxd3.d3.dsv;

import com.github.javafxd3.d3.core.ObjectAccessor;

/**
 * An accessor function which is passed to {@link Dsv#parse(String, DsvObjectAccessor)}.
 *  
 * @param <T>
 */
public interface DsvObjectAccessor<T> extends ObjectAccessor<Object, T> {}
