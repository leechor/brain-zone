package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IElementObjects extends INamedSimioCollection<IElementObject> {
    IElementObject createElement(String typeName);
}
