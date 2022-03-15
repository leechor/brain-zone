package org.licho.brain.simuApi;

/**
 *
 */
public interface IElementObjects extends INamedSimioCollection<IElementObject> {
    IElementObject createElement(String typeName);
}
