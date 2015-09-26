package com.github.javafxd3.api.arrays;


import com.github.javafxd3.api.arrays.ForEachCallback;
import com.github.javafxd3.api.arrays.NumericForEachCallback;
import com.github.javafxd3.api.core.Value;

/**
 * 
 * 
 */
public class Callbacks {

    /**
     * Return a callback that return true only if the element is greater than the given value.
     * 
     * @param than
     * @return
     */
    public static ForEachCallback<Boolean> greaterThan(final double than) {
        return new ForEachCallback<Boolean>() {
            @Override
            public Boolean forEach(final Object thisArg, final Value element, final int index, final Object[] array) {
                System.out.println("received " + element.asDouble() + " > " + than + " : "
                        + (element.asDouble() > than));
                return element.asDouble() > than;
            }
        };
    }

    /**
     * Return a callback that add the given int to each numeric element
     * 
     * @param i the number to add
     * @return the callback
     */
    public static NumericForEachCallback add(final int i) {
        return new NumericForEachCallback() {
            @Override
            public double forEach(final Object thisArg, final Value element, final int index, final Object[] array) {
                return element.asDouble() + i;
            }
        };
    }
}
