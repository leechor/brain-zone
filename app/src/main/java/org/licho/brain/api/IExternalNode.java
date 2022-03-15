package org.licho.brain.api;

/**
 *
 */
public interface IExternalNode {
    String getName();
    void setName(String name);
    IElementReferenceInitialValuesCollection getInitialPropertyValues(IModel model);
}
