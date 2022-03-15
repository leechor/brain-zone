package org.licho.brain.brainApi;

/**
 *
 */
public interface IMutableSimioCollection<T> {
    T create();

    boolean remove(T item);

    void remoteAt(int index);

    void removeRange(int index, int count);

    void clear();
}
