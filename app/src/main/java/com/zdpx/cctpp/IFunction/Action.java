package com.zdpx.cctpp.IFunction;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
@FunctionalInterface
public interface Action<T> {
    void apply(T t);
}
