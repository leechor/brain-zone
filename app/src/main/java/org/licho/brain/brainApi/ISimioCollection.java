package org.licho.brain.brainApi;

/**
 *
 */
public interface ISimioCollection<T> extends Iterable<T>{
    int Count();

    T getByIndex(int index);
}
