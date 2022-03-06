package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface INamedMutableSimioCollection<T> extends IMutableSimioCollection<T> {
    T create(String name);
}
