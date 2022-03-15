package org.licho.brain.brainApi;

/**
 *
 */
public interface ITable {

    String Name();

    void Name(String name);

    IRows getRows();
}
