package org.licho.brain.brainApi;

/**
 *
 */
public interface IProperty {
    String getName();

    String Value();

    void Value(String value);

    IUnitBase Unit();
}
