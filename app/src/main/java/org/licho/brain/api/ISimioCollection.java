package org.licho.brain.api;

/**
 *
 */
public interface ISimioCollection<T> extends Iterable<T>{
    int Count();

    T getByIndex(int index);
}
