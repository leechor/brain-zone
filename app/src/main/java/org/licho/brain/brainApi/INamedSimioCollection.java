package org.licho.brain.brainApi;

/**
 *
 */
public interface INamedSimioCollection<T> extends ISimioCollection<T> {
    T getByName(String name);
}
