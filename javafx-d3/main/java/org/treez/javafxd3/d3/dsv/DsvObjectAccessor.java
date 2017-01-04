package org.treez.javafxd3.d3.dsv;

import org.treez.javafxd3.d3.core.ObjectAccessor;

/**
 * An accessor function which is passed to Dsv#parse(String, DsObjectAccessor)}.
 *  
 * @param <T>
 */
public interface DsvObjectAccessor<T> extends ObjectAccessor<Object, T> {}
