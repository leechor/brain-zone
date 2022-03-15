package org.licho.brain.api;

/**
 *
 */
public interface IElementObjects extends INamedSimioCollection<IElementObject> {
    IElementObject createElement(String typeName);
}
