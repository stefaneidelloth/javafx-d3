package com.github.javafxd3.api.dsv;

import com.github.javafxd3.api.core.ObjectAccessor;

/**
 * An accessor function which is passed to {@link Dsv#parse(String, DsvObjectAccessor)}.
 *  
 * @param <T>
 */
public interface DsvObjectAccessor<T> extends ObjectAccessor<DsvRow, T> {}
