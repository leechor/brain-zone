package org.licho.brain.api;

/**
 *
 */
public interface INamedList {
    String Name();

    String Description();

    void Description(String description);

    IRows Rows();
}
