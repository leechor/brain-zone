package org.licho.brain.brainApi;

/**
 *
 */
public interface IExternalNode {
    String getName();
    void setName(String name);
    IElementReferenceInitialValuesCollection getInitialPropertyValues(IModel model);
}
