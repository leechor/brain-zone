package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IExternalNode {
    String getName();
    void setName(String name);
    IElementReferenceInitialValuesCollection getInitialPropertyValues(IModel model);
}
