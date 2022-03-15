package org.licho.brain.simuApi;

/**
 *
 */
public interface INamedMutableSimioCollection<T> extends IMutableSimioCollection<T> {
    T create(String name);
}
