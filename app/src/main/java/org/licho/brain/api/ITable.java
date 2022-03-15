package org.licho.brain.api;

/**
 *
 */
public interface ITable {

    String Name();

    void Name(String name);

    IRows getRows();
}
