package org.licho.brain.simuApi;

/**
 *
 */
public interface IProperty {
    String getName();

    String Value();

    void Value(String value);

    IUnitBase Unit();
}
