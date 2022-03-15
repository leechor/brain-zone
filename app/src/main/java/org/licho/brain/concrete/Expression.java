package org.licho.brain.concrete;

/**
 *
 */
public interface Expression<T> {
    T value(Object calculation);
}
