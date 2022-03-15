package org.licho.brain.api;

/**
 *
 */
public interface INamedSimioCollection<T> extends ISimioCollection<T> {
    T getByName(String name);
}
