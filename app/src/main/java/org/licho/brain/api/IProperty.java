package org.licho.brain.api;

/**
 *
 */
public interface IProperty {
    String getName();

    String Value();

    void Value(String value);

    IUnitBase Unit();
}
