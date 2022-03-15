package org.licho.brain.brainApi;

/**
 *
 */
public interface INamedList {
    String Name();

    String Description();

    void Description(String description);

    IRows Rows();
}
