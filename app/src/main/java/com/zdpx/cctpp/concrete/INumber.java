package com.zdpx.cctpp.concrete;

/**
 *
 */
public interface INumber<T> {
    T get();
    T minimum();
    T maximum();
    T average();
}
