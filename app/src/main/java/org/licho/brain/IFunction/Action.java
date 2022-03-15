package org.licho.brain.IFunction;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
@FunctionalInterface
public interface Action<T> {
    void apply(T t);
}
