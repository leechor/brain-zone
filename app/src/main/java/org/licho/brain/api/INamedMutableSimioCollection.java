package org.licho.brain.api;

/**
 *
 */
public interface INamedMutableSimioCollection<T> extends IMutableSimioCollection<T> {
    T create(String name);
}
