package org.licho.brain.simuApi;

/**
 *
 */
public interface INamedList {
    String Name();

    String Description();

    void Description(String description);

    IRows Rows();
}
