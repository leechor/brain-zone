package org.licho.brain.brainApi;

/**
 *
 */
public interface IElementObjects extends INamedSimioCollection<IElementObject> {
    IElementObject createElement(String typeName);
}
