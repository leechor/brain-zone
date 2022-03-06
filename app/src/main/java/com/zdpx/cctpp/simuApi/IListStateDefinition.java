package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IListStateDefinition {
    INamedList getStringList();

    void setStringList(INamedList namedList);

    String initialValue(String name);

    void setInitialValue(String name);
}
