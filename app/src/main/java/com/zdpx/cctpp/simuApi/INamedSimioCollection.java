package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface INamedSimioCollection<T> extends ISimioCollection<T> {
    T getByName(String name);
}
