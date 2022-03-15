package org.licho.brain.simuApi;

/**
 *
 */
public interface INamedSimioCollection<T> extends ISimioCollection<T> {
    T getByName(String name);
}
