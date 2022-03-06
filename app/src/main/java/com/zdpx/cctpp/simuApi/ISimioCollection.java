package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface ISimioCollection<T> extends Iterable<T>{
    int Count();

    T getByIndex(int index);
}
