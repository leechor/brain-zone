package org.licho.brain.brainApi;

/**
 *
 */
public interface INamedMutableSimioCollection<T> extends IMutableSimioCollection<T> {
    T create(String name);
}
