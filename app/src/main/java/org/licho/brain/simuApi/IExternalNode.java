package org.licho.brain.simuApi;

/**
 *
 */
public interface IExternalNode {
    String getName();
    void setName(String name);
    IElementReferenceInitialValuesCollection getInitialPropertyValues(IModel model);
}
